CREATE TABLE IF NOT EXISTS doc COMMENT 'Тип документа пользователя' (
    id            INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version       INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    code          VARCHAR(255) UNIQUE,
    name          VARCHAR(255)
) ;
CREATE INDEX UX_doc_code ON doc(code);

CREATE TABLE IF NOT EXISTS country COMMENT 'Страна пользователя' (
    id            INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version       INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    code          VARCHAR(255) UNIQUE,
    name          VARCHAR(255)
) ;
CREATE INDEX UX_country_code ON country(code);

CREATE TABLE IF NOT EXISTS organization (
    id             INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version        INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    name           VARCHAR(255),
    full_name      VARCHAR(255),
    inn            VARCHAR(255),
    kpp            VARCHAR(255),
    address        VARCHAR(4000),
    phone          VARCHAR(255),
    is_active      BOOLEAN
);

CREATE TABLE IF NOT EXISTS office (
    id             INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version        INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    org_id         INTEGER,
    FOREIGN KEY (org_id) REFERENCES organization(id) ON DELETE CASCADE,
    name           VARCHAR(255),
    address        VARCHAR(4000),
    phone          VARCHAR(255),
    is_active      BOOLEAN
);
CREATE INDEX IX_office_org_id ON office(org_id);

CREATE TABLE IF NOT EXISTS user (
    id             INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version        INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    office_id      INTEGER,
    FOREIGN KEY (office_id) REFERENCES office(id) ON DELETE CASCADE,
    first_name     VARCHAR(255),
    second_name    VARCHAR(255),
    middle_name    VARCHAR(255),
    position       VARCHAR(255),
    phone          VARCHAR(255),
    citizenship_id INTEGER(255),
    FOREIGN KEY (citizenship_id) REFERENCES country(id) ON DELETE RESTRICT,
    is_identified  BOOLEAN
);
CREATE INDEX IX_user_office_id ON user(office_id);
CREATE INDEX IX_user_citizenship_id ON user(citizenship_id);

CREATE TABLE IF NOT EXISTS user_doc (
    id             INTEGER              COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE,
    version        INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    doc_id         INTEGER              COMMENT 'Идентификатор типа документа',
    FOREIGN KEY (doc_id) REFERENCES doc(id) ON DELETE RESTRICT,
    doc_name       VARCHAR(255)         COMMENT 'Название документа (актуально для прочих документов)',
    doc_number     VARCHAR(255)         COMMENT 'Номер документа',
    doc_date       DATE                 COMMENT 'Дата выдачи документа'
);
CREATE INDEX IX_user_doc_id ON user_doc(doc_id);
