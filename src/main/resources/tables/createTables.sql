CREATE TABLE DEPARTMENTS (
	dept_id 		varchar(3) 			not null,
	dept_name 		varchar(50) 		not null,
	budget 			int unsigned 		default 0 not null,
	founding_date 	timestamp 			not null,
	
	PRIMARY KEY(dept_id)
);

CREATE TABLE PERSON (
	ID 				int unsigned 		auto_increment,
	first_name 		varchar(26) 		not null,
	last_name 		varchar(26) 		not null,
	street_address 	varchar(50),
	city 			varchar(26),
	state 			varchar(26),
	zip 			varchar(26),
	country 		varchar(26),
	phone_number 	varchar(26),
	email 			varchar(26) 		not null,
	gender 			varchar(1),
	sin 			varchar(8),
	dept_id 	 	varchar(3),
	
	PRIMARY KEY(ID, dept_id),
	
	FOREIGN KEY(dept_id)
	  REFERENCES DEPARTMENTS(dept_id),
	
	CONSTRAINT name_cnt UNIQUE(first_name, last_name, email)
);

CREATE TABLE EMPLOYEES (
	person_id 	int unsigned 			not null,
	start_date 	timestamp 				default current_timestamp not null,
	rank 		int unsigned 			default 5,
	salary 		int unsigned,
	
	PRIMARY KEY(person_id),
	
	FOREIGN KEY(person_id)
	  REFERENCES PERSON(id)
);

CREATE TABLE EMPLOYEES_HISTORY (
	person_id 	int unsigned 			not null,
	start_date 	timestamp 				not null,
	end_date 	timestamp 				default current_timestamp not null,
	rank 		int unsigned,
	salary 		int unsigned,
	
	PRIMARY KEY(person_id, start_date, end_date),
	
	FOREIGN KEY(person_id)
	  REFERENCES PERSON(id)
);

CREATE TABLE STUDENTS (
	person_id 	int unsigned 			not null,
	admission_status 	varchar(26) 	not null,
	major 		varchar(26) 			not null,
	minor 		varchar(26),
	credit 		int unsigned 			default 0 not null,
	start_date 	timestamp 				default current_timestamp not null,
	
	PRIMARY KEY(person_id),
	
	FOREIGN KEY(person_id)
	  REFERENCES PERSON(id)
);

CREATE TABLE STUDENTS_HISTORY (
	person_id 	int unsigned 			not null,
	admission_status 	varchar(26) 	not null,
	major 		varchar(26) 			not null,
	minor 		varchar(26),
	credit 		int unsigned 			not null,
	start_date 	timestamp 				not null,
	end_date 	timestamp 				default current_timestamp not null,
	
	PRIMARY KEY(person_id, start_date, end_date),
	
	FOREIGN KEY(person_id)
	  REFERENCES PERSON(id)
);

CREATE TABLE USERS (
	username 		varchar(26),
	password 		varchar(26)  		not null,
	owner_id 		int unsigned 		not null,
	authority 		int unsigned 		default 5 not null,
	
	PRIMARY KEY(username),
	
	FOREIGN KEY (owner_id)
	  REFERENCES PERSON(id)
);

CREATE TABLE MESSAGES (
	message_id 		int unsigned 		auto_increment,
	message_header 	varchar(50) 		not null,
	message_body 	varchar(1500) 		not null,
	label_date 		timestamp 			default current_timestamp not null,
	sender_id 		int unsigned 		not null,
	receiver_id 	int unsigned 		not null,
	priority 		int unsigned 		default 5 not null,
	message_type 	varchar(26) 		default 'message' not null,
	
	PRIMARY KEY(message_id),
	
	FOREIGN KEY(sender_id)
	  REFERENCES PERSON(id),
	
	FOREIGN KEY(receiver_id)
	  REFERENCES PERSON(id)
);

CREATE TABLE BUILDINGS (
	building_id 	int unsigned 		auto_increment,
	address 		varchar(100) 		not null,
	dept_id 		varchar(3) 			not null,
	
	PRIMARY KEY(building_id),
	
	FOREIGN KEY(dept_id)
	  REFERENCES DEPARTMENTS(dept_id)
);

CREATE TABLE ROOMS (
 	room_id 		int unsigned 		not null,
 	building_id 	int unsigned 		not null,
 	floor 			int 				not null,
 	
 	PRIMARY KEY(room_id, building_id),
 	
 	FOREIGN KEY(building_id)
 	  REFERENCES BUILDINGS(building_id)
);

CREATE TABLE COURSES (
	dept_id 		varchar(3) 			not null,
	course_id 		int unsigned 		not null,
	section 		int unsigned 		not null,
	credit 			int unsigned 		not null,
	semester 		varchar(15) 		not null,
	year 			int unsigned 		not null,
	capacity 		int unsigned 		not null,
	wait_list 		int unsigned 		not null,
	
	PRIMARY KEY(dept_id, course_id, section),
	
	FOREIGN KEY(dept_id)
	  REFERENCES DEPARTMENTS(dept_id)
);

CREATE TABLE PRE_REQUISITE (
	course_dept 	varchar(3) 			not null,
	course_id 		int unsigned  		not null,
	pre_req_dept 	varchar(3) 			not null,
	pre_req_id 		int unsigned 		not null,
	
	PRIMARY KEY(course_dept, course_id, pre_req_dept, pre_req_id),
	
	FOREIGN KEY(course_dept, course_id)
	  REFERENCES COURSES(dept_id, course_id),
	  
	FOREIGN KEY(pre_req_dept, pre_req_id)
	  REFERENCES COURSES(dept_id, course_id)
);

CREATE TABLE COURSE_INSTRUCTORS (
	person_id 		int unsigned 		not null,
	dept_id 		varchar(3) 			not null,
	course_id 		int unsigned 		not null,
	section 		int unsigned 		not null,
	
	PRIMARY KEY(person_id, dept_id, course_id, section),
	
	FOREIGN KEY(person_id)
	  REFERENCES EMPLOYEES(person_id),
	
	FOREIGN KEY(dept_id, course_id, section)
	  REFERENCES COURSES(dept_id, course_id, section)
);

CREATE TABLE SCHEDULES (
	room_id 		int unsigned 		not null,
	dept_id 		varchar(3) 			not null,
	course_id 		int unsigned 		not null,
	section 		int unsigned 		not null,
	start_time 		timestamp 			not null,
	end_time 		timestamp 			not null,
	day_of_week 	int unsigned 		not null,
	
	PRIMARY KEY(room_id, dept_id, course_id, section, day_of_week),
	
	FOREIGN KEY(room_id)
	  REFERENCES ROOMS(room_id),
	
	FOREIGN KEY(dept_id, course_id, section)
	  REFERENCES COURSES(dept_id, course_id, section)
);