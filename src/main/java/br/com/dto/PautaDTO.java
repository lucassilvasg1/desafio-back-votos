package br.com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO
{
   private Long id;

   private String descricao;
   
   private SessaoDTO sessao;
}
