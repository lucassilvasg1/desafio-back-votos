package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.VotoDTO;
import br.com.service.VotoService;

@RestController
@RequestMapping("voto")
@CrossOrigin(origins = "*")
public class VotoController
{

   @Autowired
   VotoService service;

   @PostMapping
   public ResponseEntity<VotoDTO> inserir(@Valid @RequestBody VotoDTO dto)
   {
      return ResponseEntity.ok(service.inserir(dto));
   }
   
}