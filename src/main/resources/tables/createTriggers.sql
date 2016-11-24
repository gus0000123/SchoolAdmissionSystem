DELIMITER ///
CREATE TRIGGER students_history_insertion 
	BEFORE DELETE 
		ON STUDENTS FOR EACH ROW
	
	BEGIN
		IF OLD.admission_status = 'graduated'
		THEN
			INSERT INTO STUDENTS_HISTORY(person_id, admission_status, major, minor, credit, start_date)
				SELECT person_id, admission_status, major, minor, credit, start_date
				FROM STUDENTS WHERE person_id = OLD.person_id;
		END IF;
	END;
///
DELIMITER ;

DELIMITER ///
CREATE TRIGGER employees_history_insertion 
	BEFORE DELETE 
		ON EMPLOYEES FOR EACH ROW
	
	BEGIN
		INSERT INTO EMPLOYEES_HISTORY(person_id, start_date, rank, salary)
			SELECT person_id, start_date, rank, salary
			FROM EMPLOYEES WHERE person_id = OLD.person_id;
	END;
///
DELIMITER ;