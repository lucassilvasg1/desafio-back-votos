package br.com.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.model.Pauta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoDTO
{
   private Long id;
   
   private Long idPauta;

   private Pauta pauta;

   private LocalDateTime dataInicio;

   private LocalDateTime dataFim;

   private List<VotoDTO> votos;
}
