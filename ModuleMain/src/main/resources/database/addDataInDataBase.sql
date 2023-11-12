--Add in DB genres
INSERT INTO genres (name) VALUES ('Аниме'),
                                 ('Биографический'), ('Боевик'),
                                 ('Вестерн'), ('Военный'),
                                 ('Детектив'), ('Детский'), ('Документальный'), ('Драма'),
                                 ('Исторический'),
                                 ('Кинокомикс'), ('Комедия'), ('Концерт'), ('Короткометражный'), ('Криминал'),
                                 ('Мелодрама'), ('Мистика'), ('Музыка'), ('Мультфильм'), ('Мюзикл'),
                                 ('Научный'), ('Нуар'),
                                 ('Приключения'),
                                 ('Реалити-шоу'),
                                 ('Семейный'), ('Спорт'),
                                 ('Ток-шоу'), ('Триллер'),
                                 ('Ужасы'),
                                 ('Фантастика'), ('Фэнтези');

--Add in DB mpaas
INSERT INTO mpaas (name) VALUES ('G'),
                                ('PG'),
                                ('PG-13'),
                                ('R-17'),
                                ('R+'),
                                ('Rx');

--Add in DB roles
INSERT INTO roles (name) VALUES ('ROLE_OVERLORD'),
                                ('ROLE_SUBADMIN'),
                                ('ROLE_USER');

--Create OVERLORD
INSERT INTO users (dtype, role_id, name, email, password, overlord, registration_date)
VALUES ('Admin', 1, 'ADMIN_OVERLORD', 'adminOverlord@mail.com', '{noop}000', true, now());