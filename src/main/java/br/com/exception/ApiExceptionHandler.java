package br.com.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler
{

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<Erro> handlerConstraintViolationException(ConstraintViolationException exception)
   {

      Erro erro = Erro.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Field validation erro")
            .detail(exception.getMessage()).build();

      return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Erro> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception)
   {
      Erro erro = Erro.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Field validation erro")
            .detail(exception.getLocalizedMessage()).build();
      
      return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(SessaoException.class)
   public ResponseEntity<Erro> handlerSessaoException(SessaoException exception) 
   {
      Erro erro = Erro.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Sessao Exception")
            .detail(exception.getMessage()).build();
      
       return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
   }
   
}