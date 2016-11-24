/*
 * Made by: Mathieu Legault
 * Creation date: Jun 14, 2016
 */
package hibernate;

import beans.Person;
import beans.Student;
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
    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();
        Person p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        HibernateUtil.insert(p);
        Student s = new Student();
        s.setAdmissionStatus(AdmissionStatus.PENDING);
        s.setCredit(10);
        s.setMajor("COMP");
        HibernateUtil.insert(s);
        Student s2 = (Student) p;
        HibernateUtil.update(s2);
    }
}
