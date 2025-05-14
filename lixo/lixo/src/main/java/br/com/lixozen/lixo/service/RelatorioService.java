package br.com.lixozen.lixo.service;

import br.com.lixozen.lixo.exception.ResourceNotFoundException;
import br.com.lixozen.lixo.model.Coleta;
import br.com.lixozen.lixo.model.Relatorio;
import br.com.lixozen.lixo.repository.ColetaRepository;
import br.com.lixozen.lixo.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    public List<Relatorio> listarTodos() {
        return relatorioRepository.findAll();
    }

    public Optional<Relatorio> buscarPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    public Map<String, Object> obterEstatisticasReciclagem() {
        List<Coleta> coletas = coletaRepository.findAll();

        double totalColetado = coletas.stream()
                .mapToDouble(Coleta::getQuantidadeKg)
                .sum();

        Map<String, Double> totalPorTipo = coletas.stream()
                .collect(Collectors.groupingBy(
                        Coleta::getTipoResiduo,
                        Collectors.summingDouble(Coleta::getQuantidadeKg)
                ));

        Map<String, Long> contadorColetas = coletas.stream()
                .collect(Collectors.groupingBy(
                        Coleta::getTipoResiduo,
                        Collectors.counting()
                ));

        Map<String, Object> estatisticas = new HashMap<>();
        estatisticas.put("totalColetadoKg", totalColetado);
        estatisticas.put("totalPorTipoResiduo", totalPorTipo);
        estatisticas.put("quantidadeColetasPorTipo", contadorColetas);
        estatisticas.put("quantidadeTotalColetas", coletas.size());
        estatisticas.put("dataAtualizacao", LocalDateTime.now());

        return estatisticas;
    }

    public Map<String, Object> obterEstatisticasPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Coleta> coletas = coletaRepository.findAll().stream()
                .filter(c -> !c.getDataColeta().isBefore(inicio) && !c.getDataColeta().isAfter(fim))
                .collect(Collectors.toList());

        double totalColetado = coletas.stream()
                .mapToDouble(Coleta::getQuantidadeKg)
                .sum();

        Map<String, Double> totalPorTipo = coletas.stream()
                .collect(Collectors.groupingBy(
                        Coleta::getTipoResiduo,
                        Collectors.summingDouble(Coleta::getQuantidadeKg)
                ));

        Map<String, Object> estatisticas = new HashMap<>();
        estatisticas.put("periodoInicio", inicio);
        estatisticas.put("periodoFim", fim);
        estatisticas.put("totalColetadoKg", totalColetado);
        estatisticas.put("totalPorTipoResiduo", totalPorTipo);
        estatisticas.put("quantidadeColetas", coletas.size());

        return estatisticas;
    }

    public Map<String, Object> obterEstatisticasPorTipoResiduo(String tipoResiduo) {
        List<Coleta> coletas = coletaRepository.findAll().stream()
                .filter(c -> c.getTipoResiduo().equalsIgnoreCase(tipoResiduo))
                .collect(Collectors.toList());

        double totalColetado = coletas.stream()
                .mapToDouble(Coleta::getQuantidadeKg)
                .sum();

        // Agrupar por localização
        Map<String, Double> totalPorLocalizacao = coletas.stream()
                .collect(Collectors.groupingBy(
                        Coleta::getLocalizacao,
                        Collectors.summingDouble(Coleta::getQuantidadeKg)
                ));

        Map<String, Object> estatisticas = new HashMap<>();
        estatisticas.put("tipoResiduo", tipoResiduo);
        estatisticas.put("totalColetadoKg", totalColetado);
        estatisticas.put("totalPorLocalizacao", totalPorLocalizacao);
        estatisticas.put("quantidadeColetas", coletas.size());

        return estatisticas;
    }

    public Map<String, Object> obterEstatisticasPorRegiao(String regiao) {
        List<Coleta> coletas = coletaRepository.findAll().stream()
                .filter(c -> c.getLocalizacao() != null && c.getLocalizacao().contains(regiao))
                .collect(Collectors.toList());

        double totalColetado = coletas.stream()
                .mapToDouble(Coleta::getQuantidadeKg)
                .sum();

        Map<String, Double> totalPorTipo = coletas.stream()
                .collect(Collectors.groupingBy(
                        Coleta::getTipoResiduo,
                        Collectors.summingDouble(Coleta::getQuantidadeKg)
                ));

        Map<String, Object> estatisticas = new HashMap<>();
        estatisticas.put("regiao", regiao);
        estatisticas.put("totalColetadoKg", totalColetado);
        estatisticas.put("totalPorTipoResiduo", totalPorTipo);
        estatisticas.put("quantidadeColetas", coletas.size());

        return estatisticas;
    }

    public Relatorio gerarRelatorioPersonalizado(Map<String, Object> parametros) {
        String titulo = (String) parametros.getOrDefault("titulo", "Relatório personalizado");
        String descricao = (String) parametros.getOrDefault("descricao", "Relatório gerado automaticamente");
        String geradoPor = (String) parametros.getOrDefault("geradoPor", "Sistema");

        // Extrair datas do período, se fornecidas
        LocalDate dataInicio = null;
        LocalDate dataFim = null;

        if (parametros.containsKey("dataInicio")) {
            dataInicio = LocalDate.parse((String) parametros.get("dataInicio"));
        } else {
            dataInicio = LocalDate.now().minusMonths(1);
        }

        if (parametros.containsKey("dataFim")) {
            dataFim = LocalDate.parse((String) parametros.get("dataFim"));
        } else {
            dataFim = LocalDate.now();
        }

        // Criar o relatório básico
        Relatorio relatorio = new Relatorio(titulo, descricao, dataInicio, dataFim, geradoPor);
        relatorio.setTipoRelatorio((String) parametros.getOrDefault("tipoRelatorio", "PERSONALIZADO"));
        relatorio.setFormatoArquivo((String) parametros.getOrDefault("formato", "PDF"));

        // Gerar conteúdo com base nos parâmetros
        StringBuilder conteudo = new StringBuilder();
        conteudo.append("# ").append(titulo).append("\n\n");
        conteudo.append("Descrição: ").append(descricao).append("\n");
        conteudo.append("Período: ").append(dataInicio).append(" a ").append(dataFim).append("\n\n");

        // Obter estatísticas relevantes
        Map<String, Object> estatisticas = obterEstatisticasPorPeriodo(dataInicio, dataFim);

        conteudo.append("## Estatísticas Gerais\n");
        conteudo.append("- Total coletado: ").append(estatisticas.get("totalColetadoKg")).append(" kg\n");
        conteudo.append("- Quantidade de coletas: ").append(estatisticas.get("quantidadeColetas")).append("\n\n");

        conteudo.append("## Detalhamento por Tipo de Resíduo\n");
        Map<String, Double> totalPorTipo = (Map<String, Double>) estatisticas.get("totalPorTipoResiduo");
        for (Map.Entry<String, Double> entry : totalPorTipo.entrySet()) {
            conteudo.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" kg\n");
        }

        relatorio.setConteudo(conteudo.toString());

        // Extrair métricas principais
        Map<String, String> metricas = new HashMap<>();
        metricas.put("totalColetadoKg", estatisticas.get("totalColetadoKg").toString());
        metricas.put("quantidadeColetas", estatisticas.get("quantidadeColetas").toString());
        relatorio.setMetricas(metricas);

        return relatorioRepository.save(relatorio);
    }

    public Relatorio atualizar(Long id, Relatorio novoRelatorio) {
        return relatorioRepository.findById(id)
                .map(relatorio -> {
                    relatorio.setTitulo(novoRelatorio.getTitulo());
                    relatorio.setDescricao(novoRelatorio.getDescricao());
                    relatorio.setDataInicio(novoRelatorio.getDataInicio());
                    relatorio.setDataFim(novoRelatorio.getDataFim());
                    relatorio.setConteudo(novoRelatorio.getConteudo());
                    relatorio.setTipoRelatorio(novoRelatorio.getTipoRelatorio());
                    relatorio.setFormatoArquivo(novoRelatorio.getFormatoArquivo());
                    return relatorioRepository.save(relatorio);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Relatório não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        relatorioRepository.deleteById(id);
    }
}