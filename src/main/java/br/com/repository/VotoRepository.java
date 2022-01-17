package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>
{
   Voto findByCpfAssociadoAndIdSessao(String cpfAssociado, Long idSessao);

   @Query(nativeQuery = true,  value = "SELECT count(v) FROM Votos v WHERE v.idSessao := idSessao")
   Integer quantidadeVotosSessao(@Param("idSessao") Long idSessao);

   List<Voto> findAllByIdSessao(Long idSessao);

}
