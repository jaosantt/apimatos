CREATE EXTENSION IF NOT EXISTS "pgcrypto";

ALTER TABLE cadempresa
ADD CONSTRAINT uq_cadempdocumento_cademptipo UNIQUE (cadempdocumento, cademptipo);
