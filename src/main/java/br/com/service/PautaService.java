package br.com.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dto.PautaInserirDTO;
import br.com.dto.SessaoDTO;
import br.com.dto.VotoDTO;
import br.com.model.Pauta;
import br.com.model.enumaration.TipoVoto;
import br.com.repository.PautaRepository;

@Service
public class PautaService
{
   @Autowired
   private PautaRepository pautaRepository;

   @Autowired
   private VotoService votoService;

   @Autowired
   private SessaoService sessaoService;

   @Autowired
   private ModelMapper mapper;

   @Transactional
   public PautaInserirDTO inserir(PautaInserirDTO pautaDTO)
   {
      Pauta retorno = pautaRepository.save(mapper.map(pautaDTO, Pauta.class));
      return mapper.map(retorno, PautaInserirDTO.class);
   }

   @Transactional
   public PautaInserirDTO obterPorId(Long id)
   {
      Pauta retorno = pautaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada."));
      return mapper.map(retorno, PautaInserirDTO.class);
   }

   public Integer obterQuantidadeVotos(Long idPauta)
   {
      SessaoDTO sessao = sessaoService.obterPorId(idPauta);
      return votoService.quantidadeVotosSessao(sessao.getId());
   }

   public String obterResultado(Long idPauta)
   {
      SessaoDTO sessao = sessaoService.obterPorId(idPauta);
      List<VotoDTO> listaVotos = votoService.listarVotos(sessao.getId());

      long quantidadeSim = listaVotos.stream().filter(p -> p.getTipoVoto().equals(TipoVoto.SIM)).count();
      long quantidadeNao = listaVotos.stream().filter(p -> p.getTipoVoto().equals(TipoVoto.NAO)).count();

      return "Resultado : " + (quantidadeSim > quantidadeNao ? TipoVoto.SIM : TipoVoto.NAO);
   }
}
