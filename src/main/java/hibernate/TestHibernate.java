/*
 * Made by: Mathieu Legault
 * Creation date: Jun 14, 2016
 */
package hibernate;

import beans.*;
import dao.*;
import enums.AdmissionStatus;

import org.hibernate.SessionFactory;

/**
 *
 * @author Administrator
 */
public class TestHibernate {
    static SessionFactory sessionFactory;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        sessionFactory = HibernateUtil.getSessionFactory();
        Person p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        PersonDAO.getInstance().insert(p);
        Student s = new Student();
        s.setAdmissionStatus(AdmissionStatus.PENDING);
        s.setCredit(10);
        s.setMajor("COMP");
        s.setPerson(p);
        StudentDAO.getInstance().insert(s);
        Employee e = new Employee();
        e.setPerson(p);
        EmployeeDAO.getInstance().insert(e);
    }
}
