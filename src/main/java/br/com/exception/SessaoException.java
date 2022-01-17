package br.com.exception;

public class SessaoException extends RuntimeException
{

   private static final long serialVersionUID = 954568078286476226L;

   public SessaoException(String message)
   {
      super(message);
   }

}
