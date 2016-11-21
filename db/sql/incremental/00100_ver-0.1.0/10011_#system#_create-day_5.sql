CREATE TABLE system.day (
  id          BIGINT,
  value       DATE   NOT NULL,
  type_id     BIGINT NOT NULL,
  description VARCHAR(512),
  version     BIGINT NOT NULL DEFAULT 0,
  CONSTRAINT pk_day PRIMARY KEY (id),
  CONSTRAINT fk_day_day_type FOREIGN KEY (type_id) REFERENCES system.day_type (id)
);

CREATE INDEX IF NOT EXISTS ind_day_day_type
  ON system.day (type_id);