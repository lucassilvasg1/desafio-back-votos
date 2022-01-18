
CREATE TABLE tbl_pauta
(
  id numeric(10,0) NOT NULL, 
  descricao varchar NOT NULL, 
  CONSTRAINT pk_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
CREATE SEQUENCE sq_pauta
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE tbl_sessao
(
  id numeric(10,0) NOT NULL, -- CODIGO
  id_pauta numeric(10,0) NOT NULL, 
  periodo numeric(10,0) NOT NULL, 
  data_inicio timestamp without time zone NOT NULL, 
  data_fim timestamp without time zone NOT NULL, 
  CONSTRAINT pk_sessao PRIMARY KEY (id),
  CONSTRAINT fk_sessao_pauta FOREIGN KEY (id_pauta)
	REFERENCES tbl_pauta (id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION 
)
WITH (
  OIDS=FALSE
);
CREATE SEQUENCE sq_sessao
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE tbl_voto
(
  id numeric(10,0) NOT NULL, -- CODIGO
  id_sessao numeric(10,0) NOT NULL, 
  cpf_associado varchar NOT NULL, 
  tipo_voto varchar NOT NULL, 
  CONSTRAINT pk_voto PRIMARY KEY (id),
  CONSTRAINT fk_voto_sessao FOREIGN KEY (id_sessao)
	  REFERENCES tbl_sessao (id) MATCH SIMPLE
	  ON UPDATE NO ACTION ON DELETE NO ACTION 
)
WITH (
  OIDS=FALSE
);
CREATE SEQUENCE sq_voto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
