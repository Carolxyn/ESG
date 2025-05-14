package br.com.lixozen.lixo.service;

import br.com.lixozen.lixo.model.TipoResiduo;
import br.com.lixozen.lixo.repository.TipoResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoResiduoService {

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;


    public List<TipoResiduo> listarTodos() {
        return tipoResiduoRepository.findAll();
    }


    public Optional<TipoResiduo> buscarPorId(Long id) {
        return tipoResiduoRepository.findById(id);
    }


    public TipoResiduo salvar(TipoResiduo tipoResiduo) {
        return tipoResiduoRepository.save(tipoResiduo);
    }



    public TipoResiduo atualizar(Long id, TipoResiduo novoTipoResiduo) {
        return tipoResiduoRepository.findById(id)
                .map(tipoExistente -> {

                    tipoExistente.setNome(novoTipoResiduo.getNome());
                    tipoExistente.setDescricao(novoTipoResiduo.getDescricao());
                    tipoExistente.setCodigoIdentificacao(novoTipoResiduo.getCodigoIdentificacao());
                    tipoExistente.setCorIdentificacao(novoTipoResiduo.getCorIdentificacao());
                    tipoExistente.setReciclavel(novoTipoResiduo.isReciclavel());
                    tipoExistente.setInstrucaoDescarte(novoTipoResiduo.getInstrucaoDescarte());
                    tipoExistente.setTempoDecomposicao(novoTipoResiduo.getTempoDecomposicao());

                    // Salva as alterações
                    return tipoResiduoRepository.save(tipoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Tipo de resíduo não encontrado com ID: " + id));
    }


    public boolean existe(Long id) {
        return tipoResiduoRepository.existsById(id);
    }


    public void deletar(Long id) {
        tipoResiduoRepository.deleteById(id);
    }
}
