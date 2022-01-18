package br.com.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SessaoInserirDTO
{
   @NotNull(message = "ID da pauta é obrigatório.")
   private Long idPauta;

   private Integer periodo;
}