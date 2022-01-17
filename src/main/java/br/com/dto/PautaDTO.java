package br.com.dto;

import br.com.model.Sessao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO
{
   private Long id;

   private String descricao;
   
   private Sessao sessao;

}
