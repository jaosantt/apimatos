CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cadobra (
  cadobrid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  cadobrdocumento VARCHAR(14) NOT NULL,
  cadobrrazaosocial VARCHAR(150) NOT NULL,
  cadobrnomefantasia VARCHAR(150),
  cadobrinscricaoestadual VARCHAR(20),
  cadobrinscricaomunicpal VARCHAR(20),
  cadobrtelefone VARCHAR(11),
  cadobremail VARCHAR(150),
  cadobrtipo VARCHAR(30) NOT NULL,
  cadobrendereco UUID NOT NULL,
  cadobrdtcadastro TIMESTAMP NOT NULL,
  CONSTRAINT fk_endereco FOREIGN KEY (cadobrendereco) REFERENCES cadendereco(cadendid) ON DELETE CASCADE,
  CONSTRAINT uq_cadobrdocumento_cadobrtipo UNIQUE (cadobrdocumento, cadobrtipo)
);