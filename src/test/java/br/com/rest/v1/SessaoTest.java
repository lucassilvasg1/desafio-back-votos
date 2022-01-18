package br.com.rest.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dto.PautaDTO;
import br.com.dto.PautaInserirDTO;
import br.com.dto.SessaoInserirDTO;
import br.com.service.PautaService;
import br.com.service.SessaoService;
import br.com.util.DatabaseCleaner;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class SessaoTest
{

   @Autowired
   private MockMvc mockMvc;
   
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
      this.prepararDados();
   }

   @Test
   void deveRetornar200_quandoInserirSessao() throws Exception 
   {
      PautaInserirDTO pautaInserirDTO = new PautaInserirDTO();
      pautaInserirDTO.setDescricao("Pauta Teste 2");
      PautaDTO pautaRetorno = pautaService.inserir(pautaInserirDTO);
      
      SessaoInserirDTO dto = new SessaoInserirDTO();
      dto.setIdPauta(pautaRetorno.getId());
      dto.setPeriodo(60);
      
      this.mockMvc.perform(post("/v1/sessao")
                  .contentType(MediaType.APPLICATION_JSON)
                  .accept(MediaType.APPLICATION_JSON)
                  .content(this.asJsonString(dto)))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
   }
   
   public String asJsonString(final Object obj)
   {
      try
      {
         return new ObjectMapper().writeValueAsString(obj);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   }


   public void prepararDados()
   {
      PautaInserirDTO pautaInserirDTO = new PautaInserirDTO();
      pautaInserirDTO.setDescricao("Pauta Teste 1");
      PautaDTO pautaRetorno = pautaService.inserir(pautaInserirDTO);
      
      SessaoInserirDTO sessao1 = new SessaoInserirDTO();
      sessao1.setIdPauta(pautaRetorno.getId());
      sessao1.setPeriodo(60);
      sessaoService.inserir(sessao1);
   }

}
