DROP table if exists task;
CREATE TABLE task(
	task_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id bigint NOT NULL AUTO_INCREMENT,
    milstone bigint NOT NULL,
    name varchar(20) NOT NULL,
    description text NOT NULL
);

DROP table if exists comments;
CREATE TABLE comment(
	comment_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    task_id bigint NOT NULL,
    user_id varchar(20) NOT NULL
);
drop table if exists project_user;
create table project_user(
	project_id int not null,
    user_id int NOT NULL
);

drop table if exists project_user;
create table project_state(
	project_state_id int auto_increment,
    state varchar(10) not null,
    primary key(project_state_id)
);

DROP table if exists tag;
CREATE TABLE tag(
	task_id bigint NOT NULL,
    name varchar(20) NOT NULL
    );
        
DROP table if exists milestone;
CREATE TABLE milestone(
	milestone_id BIGINT NOT NULL,
    state VARCHAR(20) NOT NULL,
    PRIMARY KEY (milestone_id)
);

drop table if exists project;
CREATE TABLE project (
	project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    state INT NOT NULL,
    name VARCHAR(20),
    admin VARCHAR(20)
    
);

-- 제약사항 설정
ALTER TABLE task
	ADD CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES project(project_id) ON UPDATE CASCADE,
    ADD CONSTRAINT fk_task_milesteon FOREIGN KEY (milesteon_id) REFERENCES milestone(milestone_id);
    
ALTER TABLE comments
	ADD CONSTRAINT fk_comment_task FOREIGN KEY (task_id) REFERENCES task(task_id) ON UPDATE CASCADE;
    
ALTER TABLE project_user
	ADD CONSTRAINT foreign key(project_id) references project(project_id);
    
AlTER TABLE tag
	ADD CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES task(task_id);
    
ALTER TABLE project
	ADD CONSTRAINT fk_project_state FOREIGN KEY (state) REFERENCES project_state(state);



