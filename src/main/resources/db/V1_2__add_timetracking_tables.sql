-- Table: xr.day
DROP TABLE IF EXISTS xr.day;
CREATE TABLE xr.day
(
  id              BIGINT                 NOT NULL,
  version         INTEGER,
  datecreated     TIMESTAMP WITHOUT TIME ZONE,
  lastupdated     TIMESTAMP WITHOUT TIME ZONE,
  user_id         BIGINT                 NOT NULL,
  date            DATE                   NOT NULL,
  type            CHARACTER VARYING(255) NOT NULL,
  targetworkhours REAL                   NOT NULL,
  CONSTRAINT day_pkey PRIMARY KEY (id),
  CONSTRAINT day_user_fkey FOREIGN KEY (user_id) REFERENCES xr.user (id)
);

-- Table: xr.item
DROP TABLE IF EXISTS xr.item;
CREATE TABLE xr.item
(
  id          BIGINT                 NOT NULL,
  version     INTEGER,
  datecreated TIMESTAMP WITHOUT TIME ZONE,
  lastupdated TIMESTAMP WITHOUT TIME ZONE,
  day         BIGINT                 NOT NULL,
  datefrom    TIME WITHOUT TIME ZONE NOT NULL,
  dateto      TIME WITHOUT TIME ZONE NOT NULL,
  notes       TEXT,
  CONSTRAINT item_pkey PRIMARY KEY (id)
);