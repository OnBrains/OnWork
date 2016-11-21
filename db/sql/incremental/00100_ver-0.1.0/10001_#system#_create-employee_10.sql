CREATE TABLE system.employee (
  id          BIGINT,
  first_name  VARCHAR(32) NOT NULL,
  second_name VARCHAR(32),
  last_name   VARCHAR(64) NOT NULL,
  male        BOOLEAN     NOT NULL,
  version     BIGINT      NOT NULL DEFAULT 0,
  CONSTRAINT pk_employee PRIMARY KEY (id)
);