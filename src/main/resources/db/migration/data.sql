-- liquibase formatted sql
-- changeset dorokhov:2
INSERT INTO employee (name, role, email)
VALUES ('John Doe', 'AUTHOR', 'john.doe@example.com'),
       ('Jane Smith', 'EXECUTOR', 'jane.smith@example.com'),
       ('Bob Johnson', 'AUTHOR', 'bob.johnson@example.com'),
       ('Alice Williams', 'EXECUTOR', 'alice.williams@example.com'),
       ('Charlie Brown', 'AUTHOR', 'charlie.brown@example.com'),
       ('Eva Davis', 'EXECUTOR', 'eva.davis@example.com'),
       ('Frank Wilson', 'AUTHOR', 'frank.wilson@example.com'),
       ('Grace Miller', 'EXECUTOR', 'grace.miller@example.com'),
       ('Henry Taylor', 'AUTHOR', 'henry.taylor@example.com'),
       ('Isabel Brown', 'EXECUTOR', 'isabel.brown@example.com');


INSERT INTO task (title, description, status, priority, author_id, executor_id, comment)
VALUES
    ('Develop Blog Feature', 'Plan and implement a new feature for the company blog.', 'IN_WAITING', 'HIGH', 1, 2, 1),
    ('Code Review for New API Endpoint', 'Conduct a code review for the recently developed API endpoint.', 'IN_PROGRESS', 'MIDDLE', 3, 1, 2),
    ('Release Version 2.0', 'Prepare and execute the release of version 2.0 of the software.', 'COMPLETED', 'LOW', 2, 3, 3),
    ('Design UI for Mobile App', 'Create wireframes and design the user interface for the upcoming mobile application.', 'IN_WAITING', 'HIGH', 4, 5, 4),
    ('Bug Fixing for Web Portal', 'Identify and fix reported bugs in the web portal.', 'IN_PROGRESS', 'MIDDLE', 6, 4, 5),
    ('Customer Support Training', 'Provide training sessions for customer support team on new product features.', 'COMPLETED', 'LOW', 5, 6, 6);


INSERT INTO comment (comment, task_id)
VALUES ('The initial steps for Task 1 have been completed. Awaiting further instructions.', 1),
       ('Currently working on Task 2. Making good progress.', 2),
       ('Task 3 has been successfully completed. Well done team!', 3),
       ('Task 4 requires additional information. Requesting input from the team.', 4),
       ('Working on Task 5. Facing a few challenges, but confident in resolving them.', 5),
       ('Task 6 has been completed ahead of schedule. Great teamwork!', 6),
       ('Task 1 review meeting scheduled for tomorrow. Preparation underway.', 1),
       ('Task 2 milestone achieved. Celebrating the progress!', 2);