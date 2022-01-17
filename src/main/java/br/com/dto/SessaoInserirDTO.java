package br.com.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoInserirDTO
{
   @NotBlank(message = "ID da pauta é obrigatório.")
   private Long idPauta;

   private Integer periodo;
}