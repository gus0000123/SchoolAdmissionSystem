package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Student;
import util.Utilities;

public class StudentDAO implements DAO<Student, Integer>
{
	@Override
	public int insert(Student o) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Student o) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeByPrimaryKey(Integer id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> getAll() throws SQLException, ClassNotFoundException
	{
		//ResultSet rs = con.executeQuery("SELECT * FROM Students");
		ArrayList<Student> result = new ArrayList<Student>();
		/*while (rs.next())
		{
			Student s = new Student();
			s.setPersonID(rs.getInt("person_id"));
			s.setAdmissionStatus(rs.getString("admission_status"));
			s.setMajor(rs.getString("major"));
			s.setMinor(rs.getString("minor"));
			s.setCredit(rs.getInt("credit"));
			s.setStartDate(Utilities.convertStringToDate(rs.getString("start_date")));
			
			result.add(s);
		}
		con.closeConnection(rs);*/
		return result;
	}

	@Override
	public Student getByPrimaryKey(Integer id) throws SQLException, ClassNotFoundException
	{
		Student s = null;
		/*ResultSet rs = con.executeQuery("SELECT * FROM STUDENTS WHERE person_id = '" + id + "'");
		while (rs.next())
		{
			s = new Student();
			s.setPersonID(rs.getInt("person_id"));
			s.setAdmissionStatus(rs.getString("admission_status"));
			s.setMajor(rs.getString("major"));
			s.setMinor(rs.getString("minor"));
			s.setCredit(rs.getInt("credit"));
			s.setStartDate(Utilities.convertStringToDate(rs.getString("start_date")));
		}
		con.closeConnection(rs);*/
		return s;
	}
}
