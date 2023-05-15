package com.ua.vladtokarenko.repository;

import com.ua.vladtokarenko.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMysqlRepository implements StudentRepository {
    private static final String DB_URL = "jdbc:mysql://robot-do-user-1968994-0.b.db.ondigitalocean.com:25060/tokarenko";
    private static final String DB_USER = "doadmin";
    private static final String DB_PASSWORD = "AVNS_I6wlDKjGszZn1wvLr9t";
    private static final String SELECT_FROM_STUDENT = "SELECT * FROM student";
    private static final String SELECT_FROM_BY_ID = "SELECT * FROM student WHERE id = ?";
    private static final String INSERT_INTO_STUDENT = "INSERT INTO student (id, age, address, name) VALUES (?, ?, ?, ?)";


    @Override
    public void save(Student student) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_STUDENT)) {
            stmt.setInt(1, student.getId());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getAddress());
            stmt.setString(4, student.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_FROM_STUDENT);
            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .age(rs.getInt("age"))
                        .address(rs.getString("address"))
                        .name(rs.getString("name"))
                        .build();

                result.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return result;
    }

    @Override
    public Student findById(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SELECT_FROM_BY_ID)) {
            stmt.setInt(1, id); // Встановлюємо значення id у запиті
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Student.builder()
                            .id(rs.getInt("id"))
                            .age(rs.getInt("age"))
                            .address(rs.getString("address"))
                            .name(rs.getString("name"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


