package ru.kuzmina.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.dto.StudentDto;
import ru.kuzmina.model.Student;

@Component
@RequiredArgsConstructor
public class StudentConverter {
    public StudentDto toDto(Student student){
        return new StudentDto(student.getId(), student.getName(), student.getMark(), student.getAge());
    }

    public Student toEntity(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getMark(), studentDto.getAge());
    }
}
