package br.com.lixozen.lixo.repository;

import br.com.lixozen.lixo.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {

    List<PontoColeta> findByCidade(String cidade);

    List<PontoColeta> findByAtivoTrue();

    @Query("SELECT p FROM PontoColeta p JOIN p.tiposResiduoAceitos t WHERE t.nome = :tipoResiduo AND p.ativo = true")
    List<PontoColeta> findByTipoResiduo(String tipoResiduo);

    @Query("SELECT p FROM PontoColeta p WHERE p.cidade = :cidade AND p.ativo = true")
    List<PontoColeta> findByCidadeAndAtivo(String cidade);
}