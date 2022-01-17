package br.com.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaInserirDTO
{
   @NotBlank(message = "O nome é obrigatório.")
   private String descricao;
}