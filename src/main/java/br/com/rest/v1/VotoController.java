package br.com.rest.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.VotoInserirDTO;
import br.com.service.VotoService;

@RestController
@RequestMapping("v1/voto")
@CrossOrigin(origins = "*")
public class VotoController
{

   @Autowired
   VotoService service;

   @PostMapping(value = "/inserir")
   public ResponseEntity<VotoInserirDTO> inserir(@Valid @RequestBody VotoInserirDTO dto)
   {
      return ResponseEntity.ok(service.inserir(dto));
   }
   
}