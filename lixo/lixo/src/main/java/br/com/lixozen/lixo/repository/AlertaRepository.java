
package br.com.lixozen.lixo.repository;

import br.com.lixozen.lixo.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findByAtivoTrue();

    List<Alerta> findByTipo(String tipo);

    @Query("SELECT a FROM Alerta a WHERE a.status = 'PENDENTE' AND a.ativo = true")
    List<Alerta> findAllPendentes();

    @Query("SELECT a FROM Alerta a WHERE a.prioridade >= :nivelPrioridade AND a.ativo = true")
    List<Alerta> findByPrioridadeMinima(Integer nivelPrioridade);
}