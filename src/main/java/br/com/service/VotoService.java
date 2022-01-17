package br.com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dto.VotoDTO;
import br.com.exception.SessaoException;
import br.com.model.Sessao;
import br.com.model.Voto;
import br.com.repository.SessaoRepository;
import br.com.repository.VotoRepository;

@Service
public class VotoService
{
   @Autowired
   private SessaoRepository sessaoRepository;

   @Autowired
   private VotoRepository votoRepository;

   @Autowired
   private ModelMapper mapper;

   @Transactional
   public VotoDTO inserir(VotoDTO votoDTO)
   {
      Sessao sessao = sessaoRepository.findByIdPauta(votoDTO.getIdPauta())
            .orElseThrow(() -> new SessaoException("Nenhuma sessão aberta para esta pauta."));

      vaildarSessao(sessao);

      vaidarVoto(votoDTO, sessao);

      Voto novoVoto = new Voto();
      novoVoto.setIdSessao(sessao.getId());
      novoVoto.setTipoVoto(votoDTO.getTipoVoto());
      novoVoto.setCpfAssociado(votoDTO.getCpfAssociado());

      Voto voto = votoRepository.save(novoVoto);
      votoDTO.setId(voto.getId());

      return votoDTO;
   }

   private void vaidarVoto(VotoDTO votoDTO, Sessao sessao)
   {
      Voto voto = votoRepository.findByCpfAssociadoAndIdSessao(votoDTO.getCpfAssociado(), sessao.getId());

      if (!Objects.isNull(voto))
      {
         throw new SessaoException("O Associado já tem um voto computado nesta pauta.");
      }
   }

   private void vaildarSessao(Sessao sessao)
   {
      if (sessao.getDataFim().isBefore(LocalDateTime.now()))
      {
         throw new SessaoException("Sessão finalizada.");
      }
   }

   public Integer quantidadeVotosSessao(Long idSessao)
   {
      return votoRepository.quantidadeVotosSessao(idSessao);
   }

   public List<VotoDTO> listarVotos(Long idSessao)
   {
      List<Voto> listaVotos = votoRepository.findAllByIdSessao(idSessao);
      return listaVotos.stream().map(voto -> mapper.map(voto, VotoDTO.class)).collect(Collectors.toList());
   }

}
