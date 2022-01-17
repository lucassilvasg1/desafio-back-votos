package br.com.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Erro
{
   private Integer status;

   private LocalDateTime timestamp;

   private String type;

   private String title;

   private String detail;

   private String userMessage;

}
