package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestRun {

    public static void main(String[] args) {
        List<Brand> lst = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sf.openSession();
        try {
            session.beginTransaction();

            lst = session.createQuery(
                    "select distinct c from brand c join fetch c.models"
            ).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for (Model model : lst.get(0).getModels()) {
            System.out.println(model);
        }

    }
}