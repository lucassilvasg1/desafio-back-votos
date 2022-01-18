package br.com.service;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dto.PautaDTO;
import br.com.dto.PautaInserirDTO;
import br.com.dto.ResultadoPautaDTO;
import br.com.dto.SessaoDTO;
import br.com.dto.VotoDTO;
import br.com.enumeration.TipoVoto;
import br.com.exception.PautaException;
import br.com.model.Pauta;
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
   public PautaDTO inserir(PautaInserirDTO pautaDTO)
   {
      Pauta retorno = pautaRepository.save(mapper.map(pautaDTO, Pauta.class));
      return mapper.map(retorno, PautaDTO.class);
   }
   
   public List<PautaDTO> pesquisar(Integer numeroPagina)
   {
      Pageable page = PageRequest.of(numeroPagina, 20);
      Page<Pauta> lista = pautaRepository.findAll(page);
      return lista.map(p -> mapper.map(p, PautaDTO.class)).toList();
   }

   public PautaDTO obterPorId(Long id)
   {
      Pauta retorno = pautaRepository.findById(id).orElseThrow(() -> new PautaException("Pauta não encontrada."));
      
      if(!Objects.isNull(retorno.getSessao()))
      {
         retorno.getSessao().setPauta(null);
      }
      
      return mapper.map(retorno, PautaDTO.class);
   }

   public ResultadoPautaDTO obterResultado(Long idPauta)
   {
      SessaoDTO sessao = sessaoService.obterPorIdPauta(idPauta);
      
      List<VotoDTO> listaVotos = votoService.listarVotos(sessao.getId());

      long quantidadeSim = listaVotos.stream().filter(p -> p.getTipoVoto().equals(TipoVoto.SIM)).count();
      long quantidadeNao = listaVotos.stream().filter(p -> p.getTipoVoto().equals(TipoVoto.NAO)).count();

      ResultadoPautaDTO retorno = new ResultadoPautaDTO();
      retorno.setQuantidadeVotosNao(quantidadeNao);
      retorno.setQuantidadeVotosSim(quantidadeSim);

      if (quantidadeSim > quantidadeNao)
      {
         retorno.setResultado("SIM");
      }
      else if (quantidadeSim == quantidadeNao)
      {
         retorno.setResultado("EMPATE");
      }
      else
      {
         retorno.setResultado("NÃO");
      }

      return retorno;
   }
}
