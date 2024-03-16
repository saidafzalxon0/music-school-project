CREATE TABLE IF NOT EXISTS admin (
                                     id BIGSERIAL PRIMARY KEY,
                                     login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );


INSERT INTO admin (login, password)
SELECT 'arxiv', '2'
    WHERE NOT EXISTS (SELECT 2 FROM admin WHERE login = 'arxiv');



