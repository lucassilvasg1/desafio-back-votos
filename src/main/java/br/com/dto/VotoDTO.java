package br.com.dto;

import br.com.enumeration.TipoVoto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoDTO
{
   private Long id;

   private Long idPauta;
   
   private String cpfAssociado;

   private TipoVoto tipoVoto;

}