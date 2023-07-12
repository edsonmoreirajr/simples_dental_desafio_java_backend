SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_tablespace = '';
SET default_with_oids = false;
SET TimeZone = 'America/Sao_Paulo';

/*******************************************************************************
   Drop Tables
********************************************************************************/

DROP TABLE IF EXISTS contato;
DROP TABLE IF EXISTS profissional;

/*******************************************************************************
   Create Types
********************************************************************************/

CREATE TYPE "public"."profissionais_cargos" AS ENUM ('Desenvolvedor', 'Designer', 'Suporte', 'Tester');

/*******************************************************************************
   Create Tables
********************************************************************************/

CREATE TABLE contato
(
    "id" BIGSERIAL,
    "nome" VARCHAR(200) NOT NULL,
    "contato" VARCHAR(25) NOT NULL,
    "created_date" DATE NOT NULL,
    "profissional_id" BIGSERIAL NOT NULL,
    CONSTRAINT "PK_Contato" PRIMARY KEY  ("id")
);

CREATE TABLE profissional
(
    "id" BIGSERIAL,
    "nome" VARCHAR(200) NOT NULL,
    "cargo" profissionais_cargos CHECK ("cargo" IN ('Desenvolvedor', 'Designer', 'Suporte', 'Tester')) NOT NULL,
    "nascimento" DATE NOT NULL,
    "created_date" DATE NOT NULL,
    CONSTRAINT "PK_Profissional" PRIMARY KEY  ("id")
);

/***************************** **************************************************
   Create Primary Key Unique Indexes and Foreign Keys
********************************************************************************/

CREATE INDEX "IFK_ContatoId" ON contato ("id");
CREATE INDEX "IFK_ProfissionalId" ON profissional ("id");

ALTER TABLE contato ADD CONSTRAINT "FK_ContatoProfissionalId"
    FOREIGN KEY ("profissional_id") REFERENCES profissional ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

/*******************************************************************************
   Populate Tables
********************************************************************************/

INSERT INTO profissional ("nome", "cargo", "nascimento", "created_date") VALUES ('Profissional_A', 'Desenvolvedor', '1986-01-02'::DATE, CURRENT_DATE);
INSERT INTO profissional ("nome", "cargo", "nascimento", "created_date") VALUES ('Profissional_B', 'Designer', '1983-09-23'::DATE, CURRENT_DATE);
INSERT INTO profissional ("nome", "cargo", "nascimento", "created_date") VALUES ('Profissional_C', 'Suporte', '1984-04-11'::DATE, CURRENT_DATE);
INSERT INTO profissional ("nome", "cargo", "nascimento", "created_date") VALUES ('Profissional_D', 'Tester', '1981-02-15'::DATE, CURRENT_DATE);

INSERT INTO contato ("nome", "contato", "created_date", "profissional_id") VALUES ('fixo casa', '3433333333', CURRENT_DATE, 1);
INSERT INTO contato ("nome", "contato", "created_date", "profissional_id") VALUES ('celular', '34999999999', CURRENT_DATE, 1);
INSERT INTO contato ("nome", "contato", "created_date", "profissional_id") VALUES ('celular', '34999999999', CURRENT_DATE, 2);
INSERT INTO contato ("nome", "contato", "created_date", "profissional_id") VALUES ('escritório', '3422222222', CURRENT_DATE, 2);
INSERT INTO contato ("nome", "contato", "created_date", "profissional_id") VALUES ('celular', '34999999999', CURRENT_DATE, 3);
INSERT INTO contato ("nome", "contato", "created_date", "profissional_id") VALUES ('escritório', '3422222222', CURRENT_DATE, 4);