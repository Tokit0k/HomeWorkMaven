package com.ua.vladtokarenko;

import com.ua.vladtokarenko.domain.Student;
import com.ua.vladtokarenko.repository.StudentMysqlRepository;
import com.ua.vladtokarenko.repository.StudentRepository;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentMysqlRepository();

        Student s1 = Student.builder()
                .id(1488)
                .age(23)
                .address("Sumy")
                .name("Vladik")
                .build();
//        studentRepository.save(s1);

//        List<Student> students = studentRepository.findAll();
//        System.out.println(students);
//        Student student = studentRepository.findById(1488);
//        System.out.println(student);



    }
}
