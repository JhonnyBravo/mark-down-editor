use mark_down_editor;

CREATE TABLE IF NOT EXISTS articles (
    id INT AUTO_INCREMENT,
    title VARCHAR(100),
    contents VARCHAR(400),
    PRIMARY KEY (id)
);
