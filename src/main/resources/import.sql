INSERT INTO tb_cargos (id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO tb_cargos (id, nome) VALUES (2, 'ROLE_MODERADOR');
INSERT INTO tb_cargos (id, nome) VALUES (3, 'ROLE_USUARIO');

INSERT INTO tb_usuarios (email, nome, senha, username) VALUES ('admin@quiz.com', 'admnistrador', '$2a$10$AHaJmGQPM654jvfmqxS4aeKYMBZj8EA44sKVoEpuvzCi5siGnWdaO', 'admin');
INSERT INTO tb_usuarios_cargos (usuario_id, cargo_id) VALUES (1, 1);
INSERT INTO tb_usuarios_cargos (usuario_id, cargo_id) VALUES (1, 2);
INSERT INTO tb_usuarios_cargos (usuario_id, cargo_id) VALUES (1, 3);

INSERT INTO tb_quiz (titulo, descricao, data_criacao, criador_id) VALUES ('Quiz de Teste', 'Quiz teste', now(), 1);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Quem pintou a Mona Lisa', 'MEDIO', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Leonardo da Vinci', true, 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Vincent van Gogh', false, 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Michelangelo', false, 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Raphael', false, 1);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual o planeta mais próximo do Sol?', 'FACIL', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Vênus', false, 2);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Marte', false, 2);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Mercúrio', true, 2);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Terra', false, 2);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual a capital da Austrália?', 'MEDIO', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Sydney', false, 3);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Melbourne', false, 3);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Brisbane', false, 3);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Canberra', true, 3);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Em que ano ocorreu a queda do Muro de Berlim?', 'DIFICIL', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('1987', false, 4);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('1988', false, 4);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('1989', true, 4);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('1990', false, 4);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual é o elemento químico de símbolo "Au"?', 'FACIL', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Prata', false, 5);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Ouro', true, 5);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Alumínio', false, 5);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Chumbo', false, 5);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual foi o primeiro animal a ser clonado com sucesso?', 'MEDIO', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Ovelha', true, 6);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Cachorro', false, 6);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Gato', false, 6);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Cavalo', false, 6);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual é a maior lua de Júpiter?', 'DIFICIL', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Europa', false, 7);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Io', false, 7);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Calisto', false, 7);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Ganimedes', true, 7);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Quem escreveu "Dom Quixote"?', 'MEDIO', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Gabriel García Márquez', false, 8);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Miguel de Cervantes', true, 8);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Pablo Neruda', false, 8);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Fernando Pessoa', false, 8);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual é a unidade de medida de resistência elétrica?', 'FACIL', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Ohm', true, 9);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Ampere', false, 9);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Volt', false, 9);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Watt', false, 9);

INSERT INTO tb_perguntas (enunciado, dificuldade, quiz_id) VALUES ('Qual é a montanha mais alta do mundo?', 'FACIL', 1);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('K2', false, 10);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Monte Everest', true, 10);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Kangchenjunga', false, 10);
INSERT INTO tb_alternativas (proposicao, correta, pergunta_id) VALUES ('Makalu', false, 10);


