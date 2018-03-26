package com.example.studentsapi.controllers;

import com.example.studentsapi.models.Student;
import com.example.studentsapi.repositories.StudentsRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    private StudentsRepository userRepository;

    @GetMapping("/students")
    public List<Student> findAllStudents() { return userRepository.findAll(); }

    @GetMapping("/students/{studentId}")
    public Student findStudentById(@PathVariable Long studentId) throws NotFoundException {

        Student foundStudent = (Student) userRepository.findOne(studentId);

        if (foundStudent == null) {
            throw new NotFoundException("Student with this ID was not found:  " + studentId);
        }

        return foundStudent;
    }

    @PostMapping("/students")
    public Student createNewStudent(@RequestBody Student newStudent) { return (Student) userRepository.save(newStudent); }

    @DeleteMapping("/students/{studentId}")
    public HttpStatus deleteStudentById(@PathVariable Long studentId) throws EmptyResultDataAccessException {

        userRepository.delete(studentId);
        return HttpStatus.OK;
    }

    @PatchMapping("/students/{studentId}")
    public Student updateStudentById(@PathVariable Long studentId, @RequestBody Student userRequest) throws NotFoundException {
        Student userFromDb = (Student) userRepository.findOne(studentId);

        if (userFromDb == null) {
            throw new NotFoundException("Student with this ID was not found: " + studentId);
        }

        userFromDb.setFirst_name(userRequest.getFirst_name());
        userFromDb.setLast_name(userRequest.getLast_name());
        userFromDb.setClasses(userRequest.getClasses());

        return (Student) userRepository.save(userFromDb);
    }

    @ExceptionHandler
    void handleStudentNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
