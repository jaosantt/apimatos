CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cadendereco (
  cadendid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  cadendlogradouro VARCHAR(100) NOT NULL,
  cadendnumero VARCHAR(8),
  cadendcomplemento VARCHAR(255),
  cadendcep VARCHAR(10) NOT NULL,
  cadendbairro VARCHAR(30) NOT NULL,
  cadendmunicipio VARCHAR(30) NOT NULL,
  cadenduf VARCHAR(2) NOT NULL,
  cadenddtcadastro TIMESTAMP NOT NULL
);