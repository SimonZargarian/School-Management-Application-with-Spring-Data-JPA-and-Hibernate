/*
 * When the application is launched this file with the name data.sql gets called and 
 * the data in this file is used to initialize the database, this is a Spring Boot 
 * auto configuration feature.
 */

INSERT INTO course_details(id, course_name, created_date, last_updated_date, is_deleted) 
values(10001, 'JPA in 50 steps', sysdate(), sysdate(), false);
INSERT INTO course_details(id, course_name, created_date, last_updated_date, is_deleted) 
values(10002, 'Spring in 50 steps', sysdate(), sysdate(), false);
INSERT INTO course_details(id, course_name, created_date, last_updated_date, is_deleted) 
values(10003, 'REST in 50 steps', sysdate(), sysdate(), false);

INSERT INTO passport_details(id, passport_number) 
values(30001, 'E12345');
INSERT INTO passport_details(id, passport_number) 
values(30002, 'E123654');
INSERT INTO passport_details(id, passport_number) 
values(30003, 'E985642');

INSERT INTO student_details(id, student_first_name, student_last_name, passport_id) 
values(20001, 'Bob', 'Erikson', 30001);
INSERT INTO student_details(id, student_first_name, student_last_name, passport_id) 
values(20002, 'Jill', 'Jonson', 30002);
INSERT INTO student_details(id, student_first_name, student_last_name, passport_id) 
values(20003, 'Erik', 'Edards', 30003);

INSERT INTO review_details(id, description_review, rating, course_id) 
values(40001, 'Good course', '4', 10001);
INSERT INTO review_details(id, description_review, rating, course_id) 
values(40002, 'Great course', '3', 10001);
INSERT INTO review_details(id, description_review, rating, course_id) 
values(40003, 'Awsome course', '5', 10003);

INSERT INTO STUDENT_COURSE(student_id, course_id)
values(20001,10001);
INSERT INTO STUDENT_COURSE(student_id, course_id)
values(20002,10001);
INSERT INTO STUDENT_COURSE(student_id, course_id)
values(20003,10001);
INSERT INTO STUDENT_COURSE(student_id, course_id)
values(20001,10003);

