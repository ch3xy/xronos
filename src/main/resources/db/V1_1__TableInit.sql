-- hibernate sequence
DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Table: xr.user
DROP TABLE IF EXISTS xr.user;
CREATE TABLE xr.user
(
  id bigint NOT NULL,
  version integer,
  datecreated timestamp without time zone,
  lastupdated timestamp without time zone,
  username character varying(255) NOT NULL,
  name character varying(255),
  surname character varying(255),
  encryptedpassword character varying(255) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  UNIQUE(username)
);