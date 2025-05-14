package br.com.lixozen.lixo.repository;

import br.com.lixozen.lixo.model.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoResiduoRepository extends JpaRepository<TipoResiduo, Long> {

    Optional<TipoResiduo> findByNome(String nome);

    List<TipoResiduo> findByReciclavel(boolean reciclavel);

    boolean existsByNome(String nome);
}