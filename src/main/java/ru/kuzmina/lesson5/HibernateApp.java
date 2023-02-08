package ru.kuzmina.lesson5;

import jakarta.persistence.EntityManager;
import ru.kuzmina.lesson5.DAO.StudentDao;

public class HibernateApp {
    public static void main(String[] args) throws Exception {
        EntityManagerUtils entityManagerUtils = EntityManagerUtils.getInstance();
        try {
            EntityManager entityManager = entityManagerUtils.getEntityManager();
            StudentDao studentDao = new StudentDao(entityManager);
            studentDao.deleteWithQuery();

            for (int i = 0; i < 10; i++){
                studentDao.save(null,"Student" + (i + 1), Math.random() * 4 + 1);
            }

            studentDao.findAll().forEach(System.out::println);
            studentDao.findById(10L).ifPresent(System.out::println);
            studentDao.save(10L, "Bob", 5.0);
            studentDao.findById(10L).ifPresent(System.out::println);
            studentDao.findByName("Bob").ifPresent(System.out::println);


            studentDao.deleteWithQuery(5L);
            studentDao.findAll().forEach(System.out::println);
            studentDao.findById(5L).ifPresent(System.out::println);
            studentDao.findById(5L).ifPresent(System.out::println);

            studentDao.deleteWithRemove(3L);
            studentDao.findById(3L).ifPresent(System.out::println);
        } finally {
            entityManagerUtils.shutDown();
        }

    }
}
