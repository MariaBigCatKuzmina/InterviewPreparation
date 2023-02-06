package ru.kuzmina.lesson5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;

public class EntityManagerUtils {
    private static EntityManagerUtils instance;

    private EntityManagerFactory sessionFactory;
    private EntityManager entityManager;


    private void init() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();

    }

    private EntityManagerUtils() {
        init();
    }

    public static EntityManagerUtils getInstance() {
        if (instance == null) {
            instance = new EntityManagerUtils();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void shutDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
