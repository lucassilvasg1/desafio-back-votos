package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.SessaoInserirDTO;
import br.com.service.SessaoService;

@RestController
@RequestMapping("sessao")
@CrossOrigin(origins = "*")
public class SessaoController
{

   @Autowired
   SessaoService service;

   @PostMapping
   public ResponseEntity<SessaoInserirDTO> inserir(@Valid @RequestBody SessaoInserirDTO dto)
   {
      return ResponseEntity.ok(service.inserir(dto));
   }

}