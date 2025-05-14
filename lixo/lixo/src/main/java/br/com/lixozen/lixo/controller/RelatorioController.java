package br.com.lixozen.lixo.controller;

import br.com.lixozen.lixo.model.Relatorio;
import br.com.lixozen.lixo.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    // GET /relatorios - Listar todos os relatórios
    @GetMapping
    public List<Relatorio> listarTodos() {
        return relatorioService.listarTodos();
    }

    // GET /relatorios/{id} - Buscar relatório por ID
    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarPorId(@PathVariable Long id) {
        return relatorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /relatorios/estatisticas - Obter estatísticas de reciclagem
    @GetMapping("/estatisticas")
    public Map<String, Object> obterEstatisticas() {
        return relatorioService.obterEstatisticasReciclagem();
    }

    // GET /relatorios/estatisticas/periodo?inicio=2023-01-01&fim=2023-01-31
    @GetMapping("/estatisticas/periodo")
    public Map<String, Object> obterEstatisticasPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return relatorioService.obterEstatisticasPorPeriodo(inicio, fim);
    }

    // GET /relatorios/tipo-residuo/{tipoResiduo}
    @GetMapping("/tipo-residuo/{tipoResiduo}")
    public Map<String, Object> obterEstatisticasPorTipoResiduo(@PathVariable String tipoResiduo) {
        return relatorioService.obterEstatisticasPorTipoResiduo(tipoResiduo);
    }

    // GET /relatorios/regiao/{regiao}
    @GetMapping("/regiao/{regiao}")
    public Map<String, Object> obterEstatisticasPorRegiao(@PathVariable String regiao) {
        return relatorioService.obterEstatisticasPorRegiao(regiao);
    }

    // POST /relatorios - Gerar novo relatório personalizado
    @PostMapping
    public Relatorio gerarRelatorio(@RequestBody Map<String, Object> parametros) {
        return relatorioService.gerarRelatorioPersonalizado(parametros);
    }
}