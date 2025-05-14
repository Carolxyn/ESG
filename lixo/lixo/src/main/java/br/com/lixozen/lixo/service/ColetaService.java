package br.com.lixozen.lixo.service;

import br.com.lixozen.lixo.model.Coleta;
import br.com.lixozen.lixo.repository.ColetaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ColetaService {

    private final ColetaRepository repository;

    public ColetaService(ColetaRepository repository) {
        this.repository = repository;
    }

    public List<Coleta> listarTodas() {
        return repository.findAll();
    }

    public Optional<Coleta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Coleta salvar(Coleta coleta) {
        return repository.save(coleta);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Coleta atualizar(Long id, Coleta novaColeta) {
        return repository.findById(id).map(c -> {
            c.setTipoResiduo(novaColeta.getTipoResiduo());
            c.setQuantidadeKg(novaColeta.getQuantidadeKg());
            c.setDataColeta(novaColeta.getDataColeta());
            c.setLocalizacao(novaColeta.getLocalizacao());
            c.setResponsavel(novaColeta.getResponsavel());
            return repository.save(c);
        }).orElseThrow(() -> new RuntimeException("Coleta não encontrada"));
    }
}
