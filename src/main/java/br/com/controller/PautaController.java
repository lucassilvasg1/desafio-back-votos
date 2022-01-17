package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.PautaInserirDTO;
import br.com.service.PautaService;

@RestController
@RequestMapping("pauta")
@CrossOrigin(origins = "*")
public class PautaController
{

   @Autowired
   PautaService service;

   @PostMapping
   public ResponseEntity<PautaInserirDTO> inserir(@Valid @RequestBody PautaInserirDTO dto)
   {
      return ResponseEntity.ok(service.inserir(dto));
   }
   
   @GetMapping(value = "/resultado/{idPauta}")
   public ResponseEntity<String> resultado(@Valid @PathVariable Long idPauta)
   {
      return ResponseEntity.ok(service.obterResultado(idPauta));
   }

}