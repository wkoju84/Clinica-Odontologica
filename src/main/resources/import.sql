-- AQUI TEMOS O SEEDING DE DADOS

INSERT INTO table_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO table_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO table_user (first_name, last_name, email, password) VALUES ('Lewis', 'Hamilton', 'hamilton@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO table_user (first_name, last_name, email, password) VALUES ('Fernando', 'Alonso', 'alonso@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
--Senha 123456

INSERT INTO table_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO table_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO table_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO dentista (nome, email, cro, atende_convenio) VALUES ('Paola Santos', 'p.santos@gmail.com', 21456, TRUE);
INSERT INTO dentista (nome, email, cro, atende_convenio) VALUES ('Monique Albuquerque', 'albuquerquedentista@gmail.com', 22563, FALSE);
INSERT INTO dentista (nome, email, cro, atende_convenio) VALUES ('Octávio Martinez', 'octaviomtnz@gmail.com', 21541, TRUE);

INSERT INTO paciente (nome, email, cpf, data_cadastro) VALUES ('Lewis Hamilton', 'hamiltonf1@gmail.com', '321654789-52', '2022-08-30');
INSERT INTO paciente (nome, email, cpf, data_cadastro) VALUES ('Charles Leclerc', 'leclerc@gmail.com', '255633021-54', '2022-08-30');
INSERT INTO paciente (nome, email, cpf, data_cadastro) VALUES ('Fernando Alonso', 'alonso-piloto@gmail.com', '541144787-95', '2022-09-01');

INSERT INTO endereco (rua, numero, bairro, cidade, cep, estado) VALUES ('Rua Farroupilha', '2154', 'Centro', 'Porto Alegre', '90600-410', 'RS');
INSERT INTO endereco (rua, numero, bairro, cidade, cep, estado) VALUES ('Rua Santos Dumont', '255', 'Cidade Baixa', 'Porto Alegre', '95555-110', 'RS');
INSERT INTO endereco (rua, numero, bairro, cidade, cep, estado) VALUES ('Servidão Almeida', '52', 'Centro', 'Florianópolis', '88020-219', 'SC');

INSERT INTO paciente_endereco (paciente_id, endereco_id) VALUES (1, 3);
INSERT INTO paciente_endereco (paciente_id, endereco_id) VALUES (2, 2);
INSERT INTO paciente_endereco (paciente_id, endereco_id) VALUES (3, 1);
INSERT INTO paciente_endereco (paciente_id, endereco_id) VALUES (2, 3);
INSERT INTO paciente_endereco (paciente_id, endereco_id) VALUES (3, 2);
INSERT INTO paciente_endereco (paciente_id, endereco_id) VALUES (3, 3);

