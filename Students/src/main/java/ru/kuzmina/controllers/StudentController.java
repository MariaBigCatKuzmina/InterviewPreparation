package ru.kuzmina.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.dto.StudentDto;
import ru.kuzmina.services.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public Page<StudentDto> getAllStudents(@RequestParam(defaultValue = "1") Integer page) {
        return studentService.getAllStudents(page);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        if (studentService.getById(id).isPresent()) {
            return studentService.getById(id).get();
        }
        return null;
    }

    @PostMapping
    public void saveStudent(@RequestBody StudentDto studentDto) {
        studentService.save(studentDto);
    }
}
