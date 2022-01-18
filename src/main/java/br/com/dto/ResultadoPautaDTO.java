package br.com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoPautaDTO
{
   private Long quantidadeVotosSim;

   private Long quantidadeVotosNao;
   
   private String resultado;
}
