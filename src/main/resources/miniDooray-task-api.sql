drop database if exists nhn_academy_2;
create database nhn_academy_2;
use nhn_academy_2;



CREATE TABLE task(
                     task_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     project_id bigint NOT NULL,
                     milestone bigint NOT NULL,
                     name varchar(20) NOT NULL,
                     description text NOT NULL
);


CREATE TABLE comments(
                         comment_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         task_id bigint NOT NULL,
                         user_id varchar(20) NOT NULL
);


create table project_user(
                             project_id bigint not null,
                             user_id varchar(20) NULL
);

create table project_state(
                              project_state_id int,
                              state varchar(10) not null,
                              primary key(project_state_id)
);


CREATE TABLE tag(
                    task_id bigint NOT NULL,
                    name varchar(20) NOT NULL
);


CREATE TABLE milestone(
                          milestone_id BIGINT NOT NULL,
                          state VARCHAR(20) NOT NULL,
                          PRIMARY KEY (milestone_id)
);


CREATE TABLE project (
                         project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         project_state_id INT NOT NULL,
                         name VARCHAR(20),
                         admin VARCHAR(20)

);

CREATE TABLE user(
                     id varchar(20) PRIMARY KEY
);



-- 제약사항 설정
ALTER TABLE task
    ADD CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES project(project_id) ON UPDATE CASCADE ON DELETE CASCADE ,
    ADD CONSTRAINT fk_task_milesteon FOREIGN KEY (milestone) REFERENCES milestone(milestone_id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comment_task FOREIGN KEY (task_id) REFERENCES task(task_id) ON UPDATE CASCADE ON DELETE CASCADE ;

ALTER TABLE project_user
    ADD CONSTRAINT foreign key(project_id) references project(project_id) ON UPDATE CASCADE ON DELETE CASCADE;

AlTER TABLE tag
    ADD CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES task(task_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE project
    ADD CONSTRAINT fk_project_state FOREIGN KEY (project_state_id) REFERENCES project_state(project_state_id);

-- 시작 데이터
INSERT INTO project_state (project_state_id ,state) VALUES (0,'ACTIVE');
INSERT INTO project_state (project_state_id, state) VALUES (1,'DORMANT');
INSERT INTO project_state (project_state_id, state) VALUES (2,'CLOSED');


