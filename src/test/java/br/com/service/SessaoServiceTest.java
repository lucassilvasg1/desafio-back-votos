package br.com.service;

import java.time.LocalDateTime;

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
import br.com.util.DatabaseCleaner;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class SessaoServiceTest
{
   @Autowired
   private PautaService pautaService;

   @Autowired
   private SessaoService sessaoService;
   
   @Autowired
   private DatabaseCleaner dataBaseCleaner;

   @BeforeEach
   public void setUp()
   {
      dataBaseCleaner.clearTables();
   }

   @DisplayName("Teste salvar sessao")
   @Test
   void testarSalvarSessao()
   {
      PautaInserirDTO pautaDTO = new PautaInserirDTO();
      pautaDTO.setDescricao("Pauta Teste");
      
      PautaDTO pautaSalva = pautaService.inserir(pautaDTO);
      
      SessaoInserirDTO sessaoDTO = new SessaoInserirDTO();
      sessaoDTO.setIdPauta(pautaSalva.getId());
      sessaoDTO.getPeriodo();

      SessaoDTO sessaoSalva = sessaoService.inserir(sessaoDTO);

      Assertions.assertNotNull(sessaoSalva.getId());
      Assertions.assertEquals(pautaSalva.getId(), sessaoSalva.getIdPauta());
      Assertions.assertTrue(LocalDateTime.now().isBefore(sessaoSalva.getDataFim()));
   }


}
