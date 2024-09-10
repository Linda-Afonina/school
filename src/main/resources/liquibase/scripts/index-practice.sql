-- liquibase formatted sql

-- changeset lafonina:1
CREATE INDEX student_name_index ON student (name);

--changeset lafonina:2
CREATE INDEX faculty_nc_index ON faculty (name, color);





