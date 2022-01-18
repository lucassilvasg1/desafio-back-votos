package br.com.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
   
   @Autowired
   private MessageSource messageSource;
   
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
         WebRequest request)
   {
      
      List<ErroObject> problemObjects = ex.getBindingResult().getAllErrors().stream().map(objectError -> {

         String name = objectError.getObjectName();
         String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

         if (objectError instanceof FieldError) {
            name = ((FieldError) objectError).getField();
         }

         return ErroObject.builder().name(name).userMessage(message).build();
         
      }).collect(Collectors.toList());
      
      Erro erro = Erro.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Argumento Inválido")
            .objects(problemObjects).build();
      
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
   
   @ExceptionHandler(VotoException.class)
   public ResponseEntity<Erro> handlerVotoException(VotoException exception) 
   {
      Erro erro = Erro.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Voto Exception")
            .detail(exception.getMessage()).build();
      
      return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(PautaException.class)
   public ResponseEntity<Erro> handlerPautaException(PautaException exception) 
   {
      Erro erro = Erro.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Pauta Exception")
            .detail(exception.getMessage()).build();
      
      return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
   }
   
}