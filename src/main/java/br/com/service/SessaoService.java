package br.com.service;

import java.util.Objects;

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
   public SessaoDTO inserir(SessaoInserirDTO sessaoDTO)
   {
      Sessao sessao = sessaoRepository.findByIdPauta(sessaoDTO.getIdPauta()).orElse(null);
      if (!Objects.isNull(sessao))
      {
         throw new SessaoException("Já existe uma sessão para esta pauta.");
      }

      Sessao retorno = sessaoRepository.save(mapper.map(sessaoDTO, Sessao.class));
      return mapper.map(retorno, SessaoDTO.class);
   }

   public SessaoDTO obterPorId(Long id)
   {
      Sessao retorno = sessaoRepository.findById(id).orElseThrow(() -> new SessaoException("Sessao não encontrada."));
      return mapper.map(retorno, SessaoDTO.class);
   }

   public SessaoDTO obterPorIdPauta(Long idPauta)
   {
      Sessao retorno = sessaoRepository.findByIdPauta(idPauta).orElseThrow(() -> new SessaoException("A Pauta não contém uma sessão."));
      return mapper.map(retorno, SessaoDTO.class);
   }

}
