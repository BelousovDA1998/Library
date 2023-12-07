CREATE TABLE IF NOT EXISTS books (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(255),
    author VARCHAR(255),
    publish_date DATE
    );

INSERT INTO books (title, author, publish_date) VALUES
                                                    ('Книга 1', 'Автор 1', '2021-01-01'),
                                                    ('Книга 2', 'Автор 2', '2021-02-01');
