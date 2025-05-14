package br.com.lixozen.lixo.service;

import br.com.lixozen.lixo.exception.ResourceNotFoundException;
import br.com.lixozen.lixo.model.PontoColeta;
import br.com.lixozen.lixo.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    public List<PontoColeta> listarTodos() {
        return pontoColetaRepository.findAll();
    }

    public Optional<PontoColeta> buscarPorId(Long id) {
        return pontoColetaRepository.findById(id);
    }

    public List<PontoColeta> buscarPorTipoResiduo(String tipoResiduo) {
        return pontoColetaRepository.findByTipoResiduo(tipoResiduo);
    }

    public List<PontoColeta> buscarPorCidade(String cidade) {
        return pontoColetaRepository.findByCidadeAndAtivo(cidade);
    }

    public PontoColeta salvar(PontoColeta pontoColeta) {
        if (pontoColeta.isAtivo() == false) {
            pontoColeta.setAtivo(true); // Por padrão, um novo ponto de coleta é ativo
        }
        return pontoColetaRepository.save(pontoColeta);
    }

    public PontoColeta atualizar(Long id, PontoColeta novoPontoColeta) {
        return pontoColetaRepository.findById(id)
                .map(pontoExistente -> {
                    pontoExistente.setNome(novoPontoColeta.getNome());
                    pontoExistente.setEndereco(novoPontoColeta.getEndereco());
                    pontoExistente.setCidade(novoPontoColeta.getCidade());
                    pontoExistente.setEstado(novoPontoColeta.getEstado());
                    pontoExistente.setCep(novoPontoColeta.getCep());
                    pontoExistente.setTelefone(novoPontoColeta.getTelefone());
                    pontoExistente.setEmail(novoPontoColeta.getEmail());
                    pontoExistente.setHorarioFuncionamento(novoPontoColeta.getHorarioFuncionamento());
                    pontoExistente.setAtivo(novoPontoColeta.isAtivo());
                    pontoExistente.setTiposResiduoAceitos(novoPontoColeta.getTiposResiduoAceitos());
                    pontoExistente.setLatidude(novoPontoColeta.getLatidude());
                    pontoExistente.setLongitude(novoPontoColeta.getLongitude());
                    return pontoColetaRepository.save(pontoExistente);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        pontoColetaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return pontoColetaRepository.existsById(id);
    }

    public List<PontoColeta> listarPontosAtivos() {
        return pontoColetaRepository.findByAtivoTrue();
    }

    public PontoColeta desativarPonto(Long id) {
        return pontoColetaRepository.findById(id)
                .map(ponto -> {
                    ponto.setAtivo(false);
                    return pontoColetaRepository.save(ponto);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com o ID: " + id));
    }

    public PontoColeta ativarPonto(Long id) {
        return pontoColetaRepository.findById(id)
                .map(ponto -> {
                    ponto.setAtivo(true);
                    return pontoColetaRepository.save(ponto);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com o ID: " + id));
    }
}