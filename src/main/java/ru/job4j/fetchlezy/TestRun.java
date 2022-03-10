package ru.job4j.fetchlezy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TestRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sf.openSession();
        try {
            session.beginTransaction();

            var rsl = session.createQuery(
                    "select distinct can from Candidate can "
                            + "join fetch can.baseVacancy bas "
                            + "join fetch bas.vacancyList lst "
                            + "where can.id = :sId", Candidate.class)
                    .setParameter("sId", 1).uniqueResult();

            System.out.println(rsl);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}