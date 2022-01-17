package br.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>
{
   Optional<Sessao> findByIdPauta(Long idPauta);
}
