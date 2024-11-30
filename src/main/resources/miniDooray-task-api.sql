drop database if exists nhn_academy_2;
create database nhn_academy_2;
use nhn_academy_2;



CREATE TABLE task(
                     task_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     project_id bigint NOT NULL,
                     milestone bigint NOT NULL,
                     name varchar(20) NOT NULL,
                     description text NOT NULL,
                     created_date DATETIME NOT NULL
);


CREATE TABLE comments(
                         comment_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         task_id bigint NOT NULL,
                         user_id varchar(20) NOT NULL,
                         content text NOT NULL,
                         created_date DATETIME NOT NULL
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
                    tag_id bigint PRIMARY KEY auto_increment,
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
                         admin VARCHAR(20),
                         created_date DATETIME NOT NULL

);

CREATE TABLE user(
                     id varchar(20) PRIMARY KEY
);



-- 시작 데이터
INSERT INTO project_state (project_state_id ,state) VALUES (0,'ACTIVE');
INSERT INTO project_state (project_state_id, state) VALUES (1,'DORMANT');
INSERT INTO project_state (project_state_id, state) VALUES (2,'CLOSED');

INSERT INTO milestone (milestone_id, state) values (0,'WAITING');
INSERT INTO milestone (milestone_id, state) values (1,'TODO');
INSERT INTO milestone (milestone_id, state) values (2,'PROCEEDING');
INSERT INTO milestone (milestone_id, state) values (3,'COMPLETED');

