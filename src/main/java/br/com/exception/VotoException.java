package br.com.exception;

public class VotoException extends RuntimeException
{

   private static final long serialVersionUID = 954568078286476226L;

   public VotoException(String message)
   {
      super(message);
   }

}
