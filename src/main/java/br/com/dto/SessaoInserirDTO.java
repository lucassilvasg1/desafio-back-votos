package br.com.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoInserirDTO
{
   @NotNull(message = "ID da pauta é obrigatório.")
   private Long idPauta;

   private Integer periodo;
}