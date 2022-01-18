package br.com.rest.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.PautaDTO;
import br.com.dto.PautaInserirDTO;
import br.com.dto.ResultadoPautaDTO;
import br.com.service.PautaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/pauta")
@CrossOrigin(origins = "*")
public class PautaController
{

   @Autowired
   PautaService service;

   @ApiOperation(value = "Este metodo insere uma nova pauta")
   @PostMapping(value= "/inserir")
   public ResponseEntity<PautaDTO> inserir(@Valid @RequestBody PautaInserirDTO dto)
   {
      return ResponseEntity.ok(service.inserir(dto));
   }

   @ApiOperation(value = "Este metodo retorna todas as pautas")
   @GetMapping(value = "/pesquisar")
   public ResponseEntity<List<PautaDTO>> pesquisar(@RequestParam Integer numeroPagina)
   {
      return ResponseEntity.ok(service.pesquisar(numeroPagina));
   }

   @ApiOperation(value = "Este metodo retorna uma pauta pelo id")
   @GetMapping(value = "/obter/{idPauta}")
   public ResponseEntity<PautaDTO> obter(@Valid @PathVariable Long idPauta)
   {
      return ResponseEntity.ok(service.obterPorId(idPauta));
   }

   @ApiOperation(value = "Este metodo retorna o resultado uma pauta pelo id")
   @GetMapping(value = "/resultado/{idPauta}")
   public ResponseEntity<ResultadoPautaDTO> resultado(@Valid @PathVariable Long idPauta)
   {
      return ResponseEntity.ok(service.obterResultado(idPauta));
   }

}