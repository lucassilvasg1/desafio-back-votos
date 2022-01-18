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
import br.com.dto.SessaoDTO;
import br.com.dto.SessaoInserirDTO;
import br.com.enumeration.TipoVoto;
import br.com.model.Voto;
import br.com.repository.VotoRepository;
import br.com.util.DatabaseCleaner;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class VotoServiceTest
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

   @DisplayName("Teste salvar voto")
   @Test
   void testarSalvarVoto()
   {
      PautaInserirDTO pautaDTO = new PautaInserirDTO();
      pautaDTO.setDescricao("Pauta Teste");
      PautaDTO pautaSalva = pautaService.inserir(pautaDTO);
      
      SessaoInserirDTO sessaoDTO = new SessaoInserirDTO();
      sessaoDTO.setIdPauta(pautaSalva.getId());
      sessaoDTO.setPeriodo(60);
      
      SessaoDTO sessaoSalva = sessaoService.inserir(sessaoDTO);
      
      Voto voto = new Voto();
      voto.setCpfAssociado("06954920398"); 
      voto.setTipoVoto(TipoVoto.SIM);
      voto.setIdSessao(sessaoSalva.getId());
      
      Voto votoSalvo = votoRepository.save(voto);
      
      Assertions.assertNotNull(votoSalvo.getId());
      Assertions.assertEquals(voto.getCpfAssociado(), votoSalvo.getCpfAssociado());
      Assertions.assertEquals(voto.getTipoVoto(), votoSalvo.getTipoVoto());
      Assertions.assertEquals(voto.getIdSessao(), sessaoSalva.getId());
   }


}
