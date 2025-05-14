package br.com.lixozen.lixo.repository;

import br.com.lixozen.lixo.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

    List<Relatorio> findByTipoRelatorio(String tipoRelatorio);

    List<Relatorio> findByDataInicioGreaterThanEqualAndDataFimLessThanEqual(LocalDate inicio, LocalDate fim);

    List<Relatorio> findByGeradoPor(String geradoPor);
}