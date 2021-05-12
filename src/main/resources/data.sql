INSERT INTO doc (id, version, code, name) VALUES
  (1, 1, '03', 'Свидетельство о рождении'),
  (2, 1, '07', 'Военный билет'),
  (3, 1, '08', 'Временное удостоверение, выданное взамен военного билета'),
  (4, 1, '10', 'Паспорт иностранного гражданина'),
  (5, 1, '11', 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу'),
  (6, 1, '12', 'Вид на жительство в Российской Федерации'),
  (7, 1, '13', 'Удостоверение беженца'),
  (8, 1, '15', 'Разрешение на временное проживание в Российской Федерации'),
  (9, 1, '18', 'Свидетельство о предоставлении временного убежища на территории Российской Федерации'),
  (10, 1, '21', 'Паспорт гражданина Российской Федерации'),
  (11, 1, '23', 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства'),
  (12, 1, '24', 'Удостоверение личности военнослужащего Российской Федерации'),
  (13, 1, '91', 'Иные документы');

INSERT INTO country (id, version, code, name) VALUES
  (1, 1, 'ru', 'Россия'),
  (2, 1, 'ua', 'Украина'),
  (3, 1, 'de', 'Германия'),
  (4, 1, 'usa', 'США'),
  (5, 1, 'gd', 'Гондурас');

INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (1, 1, 'Рога и Копыта', 'ООО "Рога и Копыта"', '111111', '1111', 'Нск, ул. Строителей, д.1', '+78008888888', true);
    INSERT INTO office (id, version, org_id, name, address, phone, is_active) VALUES (1, 1, 1, 'Головной оффис', 'Нск, ул. Строителей, д.1', '+78008888888', true);
        INSERT INTO user (id, version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id, is_identified) VALUES (1, 1, 1, 'Иванов', 'Иван', 'Иванович', 'Владелец', '+71234567890', 1, true);
            INSERT INTO user_doc (id, version, doc_id, doc_name, doc_number, doc_date) VALUES (1, 1, 10, 'Паспорт гражданина Российской Федерации', '1234 567890', DATE '2019-01-01');
        INSERT INTO user (id, version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id, is_identified) VALUES (2, 1, 1, 'Петров', 'Пётр', 'Петрович', 'Начальник', '+71234567891', 1, true);
            INSERT INTO user_doc (id, version, doc_id, doc_name, doc_number, doc_date) VALUES (2, 1, 3, 'Временное удостоверение, выданное взамен военного билета', '1234 567891', DATE '2009-10-20');
    INSERT INTO office (id, version, org_id, name, address, phone, is_active) VALUES (2, 1, 1, 'Филлиал', 'Нск, ул. Строителей, д.10', '+78008888889', true);
INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (2, 1, 'MMM', 'ООО "MMM"', '2222222', '2222', 'Омск, ул. Копателей, д.2', '+78003333333', true);
    INSERT INTO office (id, version, org_id, name, address, phone, is_active) VALUES (3, 1, 2, 'Главкасса', 'Омск, ул. Копателей, д.2', '+78003333333', true);
        INSERT INTO user (id, version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id, is_identified) VALUES (3, 1, 2, 'Сидоров', 'Сидор', 'Сидорович', 'Владелец', '+71234567892', 1, true);
            INSERT INTO user_doc (id, version, doc_id, doc_name, doc_number, doc_date) VALUES (3, 1, 10, 'Паспорт гражданина Российской Федерации', '1234 567892', DATE '2000-04-01');
INSERT INTO organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (3, 1, 'Microsoft', 'Microsoft Ltd', '3333333', '3333', 'New York', '1111111111', false);
    INSERT INTO office (id, version, org_id, name, address, phone, is_active) VALUES (4, 1, 3, 'Main campus', 'New York', '1111111111', true);
        INSERT INTO user (id, version, office_id, first_name, second_name, middle_name, position, phone, citizenship_id, is_identified) VALUES (4, 1, 4, 'Smith', 'John', 'Roland', 'Субподрядчик', '+11234567890', 4, false);
            INSERT INTO user_doc (id, version, doc_id, doc_name, doc_number, doc_date) VALUES (4, 1, 4, 'Паспорт иностранного гражданина', '987654', DATE '2010-09-11');
