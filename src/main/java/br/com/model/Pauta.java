package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_pauta")
@SequenceGenerator(name = "sq_pauta", sequenceName = "sq_pauta", allocationSize = 1)
@Setter
@Getter
@Proxy(lazy = true)
public class Pauta
{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pauta")
   private Long id;

   @Column(name = "descricao")
   private String descricao;

   @OneToOne(mappedBy = "pauta", fetch = FetchType.LAZY)
   private Sessao sessao;
}
