package ru.kuzmina.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kuzmina.converters.StudentConverter;
import ru.kuzmina.dto.StudentDto;
import ru.kuzmina.repositories.StudentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    public Page<StudentDto> getAllStudents(Integer page) {
        if (page > 0) {
            page--;
        }
        return studentRepository.findAll(PageRequest.of(page, 15, Sort.by(Sort.Direction.ASC, "id")))
                .map(studentConverter::toDto);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<StudentDto> getById(Long id) {
         return studentRepository.findById(id).map(studentConverter::toDto);
    }

    public void save(StudentDto studentDto) {
        studentRepository.save(studentConverter.toEntity(studentDto));
    }
}
