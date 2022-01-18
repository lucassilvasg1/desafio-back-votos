package br.com.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_sessao")
@SequenceGenerator(name = "sq_sessao", sequenceName = "sq_sessao", allocationSize = 1)
@Setter
@Getter
@Proxy(lazy = true)
public class Sessao
{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_sessao")
   private Long id;

   @Column(name = "id_pauta")
   private Long idPauta;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_pauta", insertable = false, updatable = false)
   private Pauta pauta;

   @Column(name = "periodo")
   private Integer periodo;

   @Column(name = "data_inicio")
   private LocalDateTime dataInicio;

   @Column(name = "data_fim")
   private LocalDateTime dataFim;

   @OneToMany(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_sessao", insertable = false, updatable = false)
   private List<Voto> votos;

   @PrePersist
   private void iniciarSessao()
   {
      dataInicio = LocalDateTime.now();

      if (Objects.isNull(periodo))
      {
         periodo = 1;
      }
      
      dataFim = LocalDateTime.now().plusMinutes(periodo);
   }
}
