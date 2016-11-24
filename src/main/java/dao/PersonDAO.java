package dao;

import java.util.List;

import beans.Person;
import hibernate.HibernateUtil;

public class PersonDAO implements DAO<Person, Integer>
{
	public int insert(Person o)
	{
		HibernateUtil.insert(o);
		/*
		PreparedStatement ps = con.getPreparedStatement("INSERT INTO Person"
				+ "(id, first_name, last_name, street_address, city, state, zip, country, phone_number, email, gender, sin) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setInt(1, o.getID());
		ps.setString(2, o.getFirstName());
		ps.setString(3, o.getLastName());
		ps.setString(4, o.getStreetAddress());
		ps.setString(5, o.getCity());
		ps.setString(6, o.getState());
		ps.setString(7, o.getPostal());
		ps.setString(8, o.getCountry());
		ps.setString(9, o.getTelephone());
		ps.setString(10, o.getEmail());
		ps.setString(11, "" + o.getGender());
		ps.setString(12, o.getSocialInsuranceNumber());
		return con.executePreparedStatement();
		*/
		return 0;
	}

	public int update(Person o)
	{
		HibernateUtil.update(o);
		return 0;
	}

	public int removeByPrimaryKey(Integer id)
	{
		HibernateUtil.deleteById(Person.class, id);
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAll()
	{
		/*
		ResultSet rs = con.executeQuery("SELECT * FROM Person");
		ArrayList<Person> result = new ArrayList<Person>();
		while (rs.next())
		{
			Person p = new Person();
			p.setID(rs.getInt("id"));
			p.setFirstName(rs.getString("first_name"));
			p.setLastName(rs.getString("last_name"));
			p.setStreetAddress(rs.getString("street_address"));
			p.setCity(rs.getString("city"));
			p.setState(rs.getString("state"));
			p.setPostal(rs.getString("zip"));
			p.setCountry(rs.getString("country"));
			p.setTelephone(rs.getString("phone_number"));
			p.setEmail(rs.getString("email"));
			p.setGender(rs.getString("gender"));
			p.setSocialInsuranceNumber(rs.getString("sin"));
			
			result.add(p);
		}
		con.closeConnection(rs);
		*/
		return HibernateUtil.loadAll(Person.class);
	}

	public Person getByPrimaryKey(Integer id)
	{
		/*
		ResultSet rs = con.executeQuery("SELECT * FROM Person WHERE id = " + id);
		Person p = new Person();
		while (rs.next())
		{
			p.setID(rs.getInt("id"));
			p.setFirstName(rs.getString("first_name"));
			p.setLastName(rs.getString("last_name"));
			p.setStreetAddress(rs.getString("street_address"));
			p.setCity(rs.getString("city"));
			p.setState(rs.getString("state"));
			p.setPostal(rs.getString("zip"));
			p.setCountry(rs.getString("country"));
			p.setTelephone(rs.getString("phone_number"));
			p.setEmail(rs.getString("email"));
			p.setGender(rs.getString("gender"));
			p.setSocialInsuranceNumber(rs.getString("sin"));
		}
		con.closeConnection(rs);
		*/
		return (Person) HibernateUtil.load(Person.class, id);
	}
	
	/*
	 * private MySQLConnection con = MySQLConnection.getInstance();
	 */
	private static PersonDAO instance = null;
	private PersonDAO() { }
	
	public static PersonDAO getInstance()
	{
		if (instance == null)
			instance = new PersonDAO();
		return instance;
	}
}
