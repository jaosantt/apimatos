CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE CADTRANSPORTADOR (
  cadtrnid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  cadtrndocumento VARCHAR(14) NOT NULL,
  cadtrnprazototal VARCHAR(150) NOT NULL,
  cadtrnnomefantasia VARCHAR(150),
  cadtrncnh VARCHAR(9),
  cadtrninscricaoestadual VARCHAR(20) NOT NULL,
  cadtrninscricaomunicipal VARCHAR(20),
  cadtrntelefone VARCHAR(11),
  cadtrnemail VARCHAR(150),
  cadtrnantt VARCHAR(9),
  cadtrntipo VARCHAR(20),
  cadtrnendereco UUID,
  CONSTRAINT fk_transp_endereco FOREIGN KEY (cadtrnendereco) REFERENCES cadendereco(cadendid) ON DELETE CASCADE
);