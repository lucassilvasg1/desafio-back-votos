package br.com.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoDTO
{
   private Long id;
   
   private Long idPauta;

   private LocalDateTime dataInicio;

   private LocalDateTime dataFim;

   private List<VotoDTO> votos;
}
