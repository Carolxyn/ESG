package br.com.lixozen.lixo.service;

import br.com.lixozen.lixo.exception.ResourceNotFoundException;
import br.com.lixozen.lixo.model.Alerta;
import br.com.lixozen.lixo.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return alertaRepository.findById(id);
    }

    public List<Alerta> listarAlertasAtivos() {
        return alertaRepository.findByAtivoTrue();
    }

    public List<Alerta> listarPorTipo(String tipo) {
        return alertaRepository.findByTipo(tipo);
    }

    public Alerta salvar(Alerta alerta) {
        if (alerta.getDataCriacao() == null) {
            alerta.setDataCriacao(LocalDateTime.now());
        }
        if (alerta.getAtivo() == null) {
            alerta.setAtivo(true);
        }
        if (alerta.getStatus() == null) {
            alerta.setStatus("PENDENTE");
        }
        return alertaRepository.save(alerta);
    }

    public Alerta atualizar(Long id, Alerta novoAlerta) {
        return alertaRepository.findById(id)
                .map(alerta -> {
                    alerta.setTitulo(novoAlerta.getTitulo());
                    alerta.setDescricao(novoAlerta.getDescricao());
                    alerta.setTipo(novoAlerta.getTipo());
                    alerta.setLocalizacao(novoAlerta.getLocalizacao());
                    alerta.setPrioridade(novoAlerta.getPrioridade());
                    alerta.setStatus(novoAlerta.getStatus());

                    // Não atualizamos a data de criação ou quem criou

                    // Se o status for alterado para RESOLVIDO, atualizamos a data de resolução
                    if ("RESOLVIDO".equals(novoAlerta.getStatus()) && alerta.getDataResolucao() == null) {
                        alerta.setDataResolucao(LocalDateTime.now());
                        alerta.setResolvidoPor(novoAlerta.getResolvidoPor());
                        alerta.setAtivo(false);
                    }

                    return alertaRepository.save(alerta);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        alertaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return alertaRepository.existsById(id);
    }

    public Alerta resolverAlerta(Long id) {
        return alertaRepository.findById(id)
                .map(alerta -> {
                    alerta.setStatus("RESOLVIDO");
                    alerta.setDataResolucao(LocalDateTime.now());
                    alerta.setAtivo(false);
                    return alertaRepository.save(alerta);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com o ID: " + id));
    }
}