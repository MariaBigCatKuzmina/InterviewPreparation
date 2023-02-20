package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuzmina.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
