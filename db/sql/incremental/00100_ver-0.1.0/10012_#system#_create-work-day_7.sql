CREATE TABLE system.work_day (
  id          BIGINT,
  employee_id BIGINT      NOT NULL,
  day_id      BIGINT      NOT NULL,
  type_id     BIGINT      NOT NULL,
  coming_time TIME WITHOUT TIME ZONE,
  out_time    TIME WITHOUT TIME ZONE,
  state       VARCHAR(32) NOT NULL DEFAULT 'NO_WORK',
  version     BIGINT,
  description VARCHAR(512),
  CONSTRAINT pk_work_day PRIMARY KEY (id),
  CONSTRAINT fk_work_day_employee FOREIGN KEY (employee_id) REFERENCES system.employee (id),
  CONSTRAINT fk_work_day_day FOREIGN KEY (day_id) REFERENCES system.day (id),
  CONSTRAINT fk_work_day_type FOREIGN KEY (type_id) REFERENCES system.day_type (id),
  CONSTRAINT uc_work_day UNIQUE (employee_id, day_id)
);

CREATE INDEX IF NOT EXISTS ind_work_day_employee
  ON system.work_day (employee_id);

CREATE INDEX IF NOT EXISTS ind_work_day_day
  ON system.work_day (day_id);

CREATE INDEX IF NOT EXISTS ind_work_day_type
  ON system.work_day (type_id);