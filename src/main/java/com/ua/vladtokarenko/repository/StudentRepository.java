package com.ua.vladtokarenko.repository;

import com.ua.vladtokarenko.domain.Student;

import java.util.List;

public interface StudentRepository {
void save(Student student);

List<Student> findAll();
Student findById(int id);
}
