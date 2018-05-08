import model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Ovidiu on 16-Apr-18.
 */
public class HibernateHelper {

    public static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void delete(Contact contact) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(contact);

        session.getTransaction().commit();

        session.close();
    }

    public static Contact findContactById(int id) {
        Session session = sessionFactory.openSession();

        Contact contact = session.get(Contact.class, id);

        session.close();

        return contact;
    }

    public static void update(Contact contact) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.update(contact);

        session.getTransaction().commit();

        session.close();
    }

    @SuppressWarnings("unchecked")
    public static List<Contact> fetchAllContacts() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Contact.class);

        List<Contact> contacts = criteria.list();

        session.close();

        return contacts;
    }

    public static int save(Contact contact) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        int id = (int) session.save(contact);

        session.getTransaction().commit();

        session.close();

        return id;
    }

}
