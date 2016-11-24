/* Insert department */
INSERT INTO DEPARTMENTS(dept_id, dept_name, budget, founding_date) VALUES("TMP","Temporary Account", 0, current_timestamp);

/* Insert a person */
INSERT INTO PERSON(first_name, last_name, email, dept_id) VALUES("Admin", "Admin", "Admin@gmail.com", "TMP");
INSERT INTO PERSON(first_name, last_name, email, dept_id) VALUES("Subadmin", "Subadmin", "Subadmin@gmail.com", "TMP");

/* Insert a student */
INSERT INTO STUDENTS(person_id, admission_status, major, credit) VALUES(2, "admitted", "comp sci", 120);

/* Insert an employee */
INSERT INTO EMPLOYEES(person_id, salary) VALUES(2, 20000); 

/* Test trigger */
UPDATE STUDENTS SET admission_status = 'graduated' WHERE person_id = 2;
DELETE FROM STUDENTS WHERE person_id = 2;
DELETE FROM EMPLOYEES WHERE person_id = 2;

/* Insert a user */
INSERT INTO USERS(username, password, owner_id) VALUES("admin", "admin", 1);
INSERT INTO USERS(username, password, owner_id) VALUES("sub", "sub", 2);

/* Insert welcome message */
INSERT INTO MESSAGES(message_header, message_body, sender_id, receiver_id)
VALUES('Welcome to School System'
	, 'WELCOME MESSAGE HERE'
	, 1, 2);
INSERT INTO MESSAGES(message_header, message_body, sender_id, receiver_id)
VALUES('Hello admin'
	, 'WELCOME MESSAGE HERE'
	, 2, 1);