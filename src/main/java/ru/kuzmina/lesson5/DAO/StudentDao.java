package ru.kuzmina.lesson5.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import ru.kuzmina.lesson5.model.Student;

import java.util.List;
import java.util.Optional;

public class StudentDao {
    private final EntityManager entityManager;

    public StudentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> findAll(){
        return entityManager.createQuery("FROM Student").getResultList();
    }

    public Student save(Long id, String name, Double mark) {
        entityManager.getTransaction().begin();
        Student student = new Student();
        try {

            student.setId(id);
            student.setName(name);
            student.setMark(mark);
            if (id == null) {
                entityManager.persist(student);
            } else {
                entityManager.merge(student);
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
        return student;
    }

    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    public Optional<Student> findByName(String name) {
        Student student;
        try {
            student = (Student) entityManager
                    .createQuery("SELECT s FROM Student s WHERE s.name like '%" + name + "%'")
                    .getSingleResult();
        } catch (NoResultException e) {
            student = null;
        }
        return Optional.ofNullable(student);
    }

    // при удалении данных из таблицы с помощью запроса, в контексе все равно остается сущность, связанная с этой записью,
    // при следующем запросе данных методом find(), объект выводится,
    // как сделать так, чтобы этот объект удалялся из контекста, ведь в БД этих данных уже нет,
    // причем если сделать запрос данных не методом find, а запросом, то при слеующем после этого вызове метода find объект
    // наконец удаляется из контекста
    public void deleteWithQuery(Long id) {
        entityManager.getTransaction().begin();
        try {
            findById(id).ifPresent(p -> entityManager.createQuery("DELETE FROM Student s WHERE s.id = " + id)
                    .executeUpdate());
            entityManager.flush();  // метод не помогает
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    public void deleteWithRemove(Long id) {
        entityManager.getTransaction().begin();
        try {
            findById(id).ifPresent(entityManager::remove);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }

    }
    public void deleteWithQuery() {
        entityManager.getTransaction().begin();
        try {
            entityManager.createQuery("DELETE FROM Student").executeUpdate();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
        }
    }

}
