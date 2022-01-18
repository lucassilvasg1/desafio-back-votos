package br.com.dto;

import javax.validation.constraints.NotNull;

import br.com.enumeration.TipoVoto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoInserirDTO
{
   @NotNull(message = "ID da pauta é obrigatório.")
   private Long idPauta;
   
   @NotNull(message = "CPF do associado é obrigatório.")
   private String cpfAssociado;

   @NotNull(message = "Tipo de voto é obrigatório.")
   private TipoVoto tipoVoto;
}
