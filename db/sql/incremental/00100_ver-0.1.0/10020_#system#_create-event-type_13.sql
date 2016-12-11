CREATE TABLE system.event_type (
  id          BIGINT,
  name        VARCHAR(128) NOT NULL,
  icon        VARCHAR(64),
  icon_color  VARCHAR(16),
  description VARCHAR(512),
  active      BOOLEAN      NOT NULL DEFAULT FALSE,
  sys         BOOLEAN      NOT NULL DEFAULT FALSE,
  dtype       VARCHAR(32)  NOT NULL,
  version     BIGINT       NOT NULL DEFAULT 0,
  CONSTRAINT pk_event_type PRIMARY KEY (id),
  CONSTRAINT uc_event_type UNIQUE (name)
);