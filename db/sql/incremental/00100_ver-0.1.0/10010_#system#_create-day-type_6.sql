CREATE TABLE system.day_type (
  id      BIGINT,
  name    VARCHAR(128) NOT NULL,
  factor  FLOAT        NOT NULL,
  dtype   VARCHAR(32)  NOT NULL,
  sys     BOOLEAN      NOT NULL DEFAULT FALSE,
  version BIGINT       NOT NULL DEFAULT 0,
  CONSTRAINT pk_day_type PRIMARY KEY (id),
  CONSTRAINT uc_day_type UNIQUE (name)
);