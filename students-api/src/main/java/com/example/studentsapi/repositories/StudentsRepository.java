package com.example.studentsapi.repositories;

import com.example.studentsapi.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsRepository extends CrudRepository {

    List<Student> findAll();
}
