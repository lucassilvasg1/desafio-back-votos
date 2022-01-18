package br.com.enumeration;

import lombok.Getter;

@Getter
public enum UsuarioStatus 
{
   UNABLE_TO_VOTE("UNABLE_TO_VOTE"), 
   ABLE_TO_VOTE("ABLE_TO_VOTE");

   private final String status;

   UsuarioStatus(String status)
   {
      this.status = status;
   }
}