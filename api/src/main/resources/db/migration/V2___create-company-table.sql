CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cadempresa (
  cadempid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  cadempdocumento VARCHAR(14) NOT NULL,
  cademprazaosocial VARCHAR(150) NOT NULL,
  cadempnomefantasia VARCHAR(150),
  cadempinscricaoestadual VARCHAR(20),
  cadempinscricaomunicpal VARCHAR(20),
  cademptelefone VARCHAR(11),
  cadempemail VARCHAR(150),
  cademptipo VARCHAR(30) NOT NULL,
  cadempendereco UUID NOT NULL,
  cadempdtcadastro TIMESTAMP NOT NULL,
  CONSTRAINT fk_endereco FOREIGN KEY (cadempendereco) REFERENCES cadendereco(cadendid) ON DELETE CASCADE
);