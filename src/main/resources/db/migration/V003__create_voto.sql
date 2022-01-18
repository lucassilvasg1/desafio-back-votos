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