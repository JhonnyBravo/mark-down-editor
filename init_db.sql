create database if not exists mark_down_editor;
use mark_down_editor;

CREATE TABLE IF NOT EXISTS articles (
    id INT AUTO_INCREMENT,
    title VARCHAR(100),
    contents VARCHAR(400),
    PRIMARY KEY (id)
);

--grant all privileges on mark_down_editor.* to user_name@localhost;
