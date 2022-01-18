package br.com.rest.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import br.com.dto.PautaInserirDTO;
import br.com.service.PautaService;
import br.com.util.DatabaseCleaner;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class PautaTest
{

   @Autowired
   private MockMvc mockMvc;
   
   @Autowired
   private PautaService pautaService;
   
   @Autowired
   private DatabaseCleaner dataBaseCleaner;
   
   @BeforeEach
   public void setUp()
   {
      dataBaseCleaner.clearTables();
      this.prepararDados();
   }

   @Test
   void deveRetornar200_quandoInserirPauta() throws Exception 
   {
      PautaInserirDTO dto = new PautaInserirDTO();
      dto.setDescricao("Pauta de Teste");
      
      this.mockMvc.perform(post("/v1/pauta/inserir")
                  .contentType(MediaType.APPLICATION_JSON)
                  .accept(MediaType.APPLICATION_JSON)
                  .content(this.asJsonString(dto)))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                  .andExpect(MockMvcResultMatchers.jsonPath("$.descricao", is("Pauta de Teste")));
   }

   @Test
   void deveRetornar200_quandoPesquisarPautas() throws Exception
   {
      this.mockMvc.perform(get("/v1/pauta/pesquisar").param("numeroPagina", "0")).andDo(print()).andExpect(status().isOk());
   }
   
   @Test
   void deveRetornar200_quandoObterPautaPorId() throws Exception
   {
      this.mockMvc.perform(get("/v1/pauta/obter/2")).andDo(print()).andExpect(status().isOk());
   }

   @Test
   void deveRetornar400_quandoNaoEncontrarPautaPorId() throws Exception
   {
      this.mockMvc.perform(get("/v1/pauta/obter/0")).andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("$.detail", is("Pauta não encontrada.")));
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
      PautaInserirDTO pauta1 = new PautaInserirDTO();
      pauta1.setDescricao("Pauta 1");
      pautaService.inserir(pauta1);
      
      PautaInserirDTO pauta2 = new PautaInserirDTO();
      pauta2.setDescricao("Pauta 2");
      pautaService.inserir(pauta2);
   }

}
