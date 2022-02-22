package ru.job4j.manytomany;

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
//            Author author1 = Author.of("Pushkin A.S.");
//            Author author2 = Author.of("Tolstoy L.N.");
//            Book book1 = Book.of("Kapitaskay dochka");
//            Book book2 = Book.of("Evgeniy Anegin");
//            Book book3 = Book.of("Skazka");
//            author1.getBooks().add(book1);
//            author1.getBooks().add(book2);
//            author1.getBooks().add(book3);
//            author2.getBooks().add(book3);
//            session.save(author1);
//            session.save(author2);

            Author author = session.get(Author.class, 2);
            session.remove(author);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}