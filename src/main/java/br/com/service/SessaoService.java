package br.com.service;

import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dto.SessaoDTO;
import br.com.dto.SessaoInserirDTO;
import br.com.exception.SessaoException;
import br.com.model.Sessao;
import br.com.repository.SessaoRepository;

@Service
public class SessaoService
{
   @Autowired
   private SessaoRepository sessaoRepository;

   @Autowired
   private ModelMapper mapper;

   @Transactional
   public SessaoInserirDTO inserir(SessaoInserirDTO sessaoDTO)
   {
      Sessao sessao = sessaoRepository.findByIdPauta(sessaoDTO.getIdPauta()).orElse(null);
      if (!Objects.isNull(sessao))
      {
         throw new SessaoException("Já existe uma sessão para esta pauta.");
      }

      Sessao retorno = sessaoRepository.save(mapper.map(sessaoDTO, Sessao.class));
      return mapper.map(retorno, SessaoInserirDTO.class);
   }

   @Transactional(readOnly = true)
   public SessaoDTO obterPorId(Long id)
   {
      Sessao retorno = sessaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sessao não encontrada."));
      return mapper.map(retorno, SessaoDTO.class);
   }

   @Transactional(readOnly = true)
   public SessaoDTO obterPorIdPauta(Long idPauta)
   {
      Sessao retorno = sessaoRepository.findByIdPauta(idPauta).orElseThrow(() -> new EntityNotFoundException("Sessao não encontrada."));
      return mapper.map(retorno, SessaoDTO.class);
   }

}
