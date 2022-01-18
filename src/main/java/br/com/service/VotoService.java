package br.com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.dto.UsuarioStatusDTO;
import br.com.dto.VotoDTO;
import br.com.dto.VotoInserirDTO;
import br.com.enumeration.UsuarioStatus;
import br.com.exception.SessaoException;
import br.com.exception.VotoException;
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
   public VotoInserirDTO inserir(VotoInserirDTO votoDTO)
   {
      Sessao sessao = sessaoRepository.findByIdPauta(votoDTO.getIdPauta()).orElseThrow(() -> new SessaoException("Nenhuma sessão aberta para esta pauta."));

      vaildarSessao(sessao);

      validarCpf(votoDTO.getCpfAssociado());
      
      vaidarVoto(votoDTO, sessao);

      Voto novoVoto = new Voto();
      novoVoto.setIdSessao(sessao.getId());
      novoVoto.setTipoVoto(votoDTO.getTipoVoto());
      novoVoto.setCpfAssociado(votoDTO.getCpfAssociado());

      votoRepository.save(novoVoto);

      return votoDTO;
   }

   public List<VotoDTO> listarVotos(Long idSessao)
   {
      List<Voto> listaVotos = votoRepository.findAllByIdSessao(idSessao);
      return listaVotos.stream().map(voto -> mapper.map(voto, VotoDTO.class)).collect(Collectors.toList());
   }

   private void validarCpf(String cpf)
   {
      try
      {
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<UsuarioStatusDTO> usuarioStatus = restTemplate.getForEntity("https://user-info.herokuapp.com/users/" + cpf, UsuarioStatusDTO.class);

         String status = Optional.of(usuarioStatus.getBody()).orElseThrow(() -> new VotoException("Erro ao validar CPF")).getStatus();

         if (UsuarioStatus.valueOf(status).equals(UsuarioStatus.UNABLE_TO_VOTE))
         {
            throw new VotoException("Associado não tem permissão para votar.");
         }

      }
      catch (HttpClientErrorException e)
      {
         if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
         {
            throw new VotoException("Cpf invalido");
         }
         else
         {
            e.printStackTrace();
            throw new VotoException("Erro ao validar CPF");
         }
      }
   }

   private void vaidarVoto(VotoInserirDTO votoDTO, Sessao sessao)
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

}
