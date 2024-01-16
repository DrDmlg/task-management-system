-- liquibase formatted sql
-- changeset dorokhov:1
CREATE TABLE employee
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50),
    role        ENUM ('AUTHOR', 'EXECUTOR'),
    email       VARCHAR(50) UNIQUE
);
-- changeset dorokhov:2
CREATE TABLE task
(
    id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status      ENUM ('IN_WAITING', 'IN_PROGRESS', 'COMPLETED'),
    priority    ENUM ('HIGH', 'MIDDLE', 'LOW'),
    author_id   BIGINT,
    executor_id BIGINT,
    comment     BIGINT,

    FOREIGN KEY (author_id) REFERENCES employee (id) ON DELETE SET NULL,
    FOREIGN KEY (executor_id) REFERENCES employee (id) ON DELETE SET NULL
);

-- changeset dorokhov:3
CREATE TABLE comment
(
    task_id BIGINT AUTO_INCREMENT,
    comment VARCHAR(255),
    FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE
);