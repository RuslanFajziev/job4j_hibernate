package ru.job4j.onetomany;

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
            ModelAuto model1 = ModelAuto.of("Kalina1");
            ModelAuto model2 = ModelAuto.of("Kalina2");
            ModelAuto model3 = ModelAuto.of("Priora1");
            ModelAuto model4 = ModelAuto.of("Priora2");
            ModelAuto model5 = ModelAuto.of("Granta");
            BrandAuto brand = BrandAuto.of("VAZ");
            brand.addModel(model1);
            brand.addModel(model2);
            brand.addModel(model3);
            brand.addModel(model4);
            brand.addModel(model5);
            session.save(brand);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}