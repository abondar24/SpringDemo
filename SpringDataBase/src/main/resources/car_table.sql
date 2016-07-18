CREATE TABLE CAR (
  ID                INT         NOT NULL AUTO_INCREMENT,
  LICENCE_PLATE     VARCHAR(20) NOT NULL,
  MANUFACTURER      VARCHAR(20) NOT NULL,
  MANUFACTURER_DATE DATE        NOT NULL,
  AGE               INT         NOT NULL DEFAULT 0,
  VERSION          INT         NOT NULL DEFAULT 0,
  UNIQUE uq_car_1(LICENCE_PLATE),
  PRIMARY KEY (ID)
);


INSERT INTO CAR (LICENCE_PLATE, MANUFACTURER, MANUFACTURER_DATE) VALUES ('LICENSE-1001', 'Ford',
                                                                        '1980-07-30');
INSERT INTO CAR (LICENCE_PLATE, MANUFACTURER, MANUFACTURER_DATE) VALUES ('LICENSE-1002', 'Toyota',
                                                                        '1992-12-30');
INSERT INTO CAR (LICENCE_PLATE, MANUFACTURER, MANUFACTURER_DATE) VALUES ('LICENSE-1003', 'BMW',
                                                                        '2003-1-6');
