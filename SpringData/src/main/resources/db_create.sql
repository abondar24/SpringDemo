CREATE TABLE contact (
  id         INT         NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(60) NOT NULL,
  last_name VARCHAR(40) NOT NULL ,
  birth_date DATE,
  UNIQUE uq_contact_1(first_name,last_name),
  PRIMARY KEY (ID)
);

CREATE TABLE contact_tel_detail (
  id         INT         NOT NULL AUTO_INCREMENT,
  contact_id INT NOT NULL,
  tel_type VARCHAR(20) NOT NULL ,
  tel_number VARCHAR(20) NOT NULL ,
  UNIQUE uq_contact_tel_detail_1(contact_id,tel_type),
  PRIMARY KEY (ID),
  CONSTRAINT fk_constraint_tel_detail_1 FOREIGN KEY (contact_id) REFERENCES contact(id)
);

INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Chris', 'Schaefer', '1981-05-03');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('Scott', 'Tiger', '1990-11-02');
INSERT INTO contact (first_name, last_name, birth_date) VALUES ('John', 'Smith', '1964-02-28');
INSERT INTO contact_tel_detail (contact_id, tel_type, tel_number) VALUES (1, 'Mobile', '1234567890');
INSERT INTO contact_tel_detail (contact_id, tel_type, tel_number) VALUES (1, 'Home', '1234567890');
INSERT INTO contact_tel_detail (contact_id, tel_type, tel_number) VALUES (2, 'Home', '1234567890');
