package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import br.com.model.enumaration.TipoVoto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_voto")
@SequenceGenerator(name = "sq_voto", sequenceName = "sq_voto", allocationSize = 1)
@Setter
@Getter
@Proxy(lazy = true)
public class Voto
{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_voto")
   private Long id;

   @Column(name = "id_sessao")
   private Long idSessao;
   
   @Column(name = "cpf_associado")
   private String cpfAssociado;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_sessao", insertable = false, updatable = false)
   private Sessao sessao;

   @Column
   @Enumerated(EnumType.STRING)
   private TipoVoto tipoVoto;

}
