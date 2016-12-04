CREATE TABLE system.day_type (
  id          BIGINT,
  name        VARCHAR(128) NOT NULL,
  factor      FLOAT        NOT NULL,
  icon        VARCHAR(64),
  icon_color  VARCHAR(16),
  description VARCHAR(512),
  dtype       VARCHAR(32)  NOT NULL,
  sys         BOOLEAN      NOT NULL DEFAULT FALSE,
  version     BIGINT       NOT NULL DEFAULT 0,
  CONSTRAINT pk_day_type PRIMARY KEY (id),
  CONSTRAINT uc_day_type UNIQUE (name)
);

INSERT INTO system.day_type (id, name, factor, icon, dtype, sys)
VALUES (1, 'Рабочий', 1.0, NULL, 'DayType', true);

INSERT INTO system.day_type (id, name, factor, icon, dtype, sys)
VALUES (2, 'Выходной', 0.0, NULL, 'DayType', true);

INSERT INTO system.day_type (id, name, factor, icon, icon_color, dtype, sys)
VALUES (3, 'Праздник', 0.0, 'fa-calendar-check-o', '#52b52f', 'DayType', true);

INSERT INTO system.day_type (id, name, factor, icon, icon_color, dtype, sys)
VALUES (4, 'Сокращенный', 0.87, 'fa-hourglass-half', '#c6d955', 'DayType', true);