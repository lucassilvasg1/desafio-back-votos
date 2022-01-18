package br.com.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import br.com.dto.PautaDTO;
import br.com.dto.PautaInserirDTO;
import br.com.dto.ResultadoPautaDTO;
import br.com.dto.SessaoDTO;
import br.com.dto.SessaoInserirDTO;
import br.com.enumeration.TipoVoto;
import br.com.model.Voto;
import br.com.repository.VotoRepository;
import br.com.util.DatabaseCleaner;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class PautaServiceTest
{
   @Autowired
   private PautaService pautaService;

   @Autowired
   private SessaoService sessaoService;
   
   @Autowired
   private VotoRepository votoRepository;

   @Autowired
   private DatabaseCleaner dataBaseCleaner;

   @BeforeEach
   public void setUp()
   {
      dataBaseCleaner.clearTables();
   }

   @DisplayName("Teste salvar pauta")
   @Test
   void testarSalvarPauta()
   {
      PautaInserirDTO pautaDTO = new PautaInserirDTO();
      pautaDTO.setDescricao("Pauta Teste");

      PautaDTO pautaSalva = pautaService.inserir(pautaDTO);

      Assertions.assertNotNull(pautaSalva.getDescricao());
      Assertions.assertEquals(pautaDTO.getDescricao(), pautaSalva.getDescricao());
      Assertions.assertNotNull(pautaSalva.getId());
   }

   @DisplayName("Teste buscar pautas")
   @Test
   void testarBuscarPautaPeloId()
   {
      PautaInserirDTO pautaDTO = new PautaInserirDTO();
      pautaDTO.setDescricao("Pauta Teste");
      PautaDTO pautaSalva = pautaService.inserir(pautaDTO);

      PautaDTO pautaRetornoBusca = pautaService.obterPorId(pautaSalva.getId());

      Assertions.assertNotNull(pautaRetornoBusca);
      Assertions.assertEquals(pautaRetornoBusca.getDescricao(), pautaDTO.getDescricao());
   }
   
   @DisplayName("Teste obter resultado da pauta")
   @Test
   void testarObterResultadoPauta()
   {
      PautaInserirDTO pautaDTO = new PautaInserirDTO();
      pautaDTO.setDescricao("Pauta Teste");
      PautaDTO pautaSalva = pautaService.inserir(pautaDTO);
      
      SessaoInserirDTO sessaoDTO = new SessaoInserirDTO();
      sessaoDTO.setIdPauta(pautaSalva.getId());
      sessaoDTO.setPeriodo(60);
      
      SessaoDTO sessaoSalva = sessaoService.inserir(sessaoDTO);
      
      Voto voto1 = new Voto();
      voto1.setCpfAssociado("06954920398");
      voto1.setTipoVoto(TipoVoto.SIM);
      voto1.setIdSessao(sessaoSalva.getId());
      
      votoRepository.save(voto1); 
      
      ResultadoPautaDTO resultadoDTO = pautaService.obterResultado(pautaSalva.getId());
      
      Assertions.assertNotNull(resultadoDTO.getQuantidadeVotosSim());
      Assertions.assertEquals(1, resultadoDTO.getQuantidadeVotosSim());
      Assertions.assertEquals(0, resultadoDTO.getQuantidadeVotosNao());
   }
   

}
