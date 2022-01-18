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