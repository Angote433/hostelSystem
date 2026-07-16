package com.ejaisoft.service;

import com.ejaisoft.Utils.PasswordUtil;
import com.ejaisoft.dao.StudentDao;
import com.ejaisoft.model.Student;

import java.util.List;

public class StudentService {
    StudentDao sd = new StudentDao();

    public void onboardStudent(Student student) {
        //Student exists?
        if (sd.existsByAdmissionNumber(student.getRegNumber())) {
            throw new RuntimeException("Student with admission " + student.getRegNumber() + " exists");
        }
        if (sd.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email " + student.getEmail() + " already exists");
        }
        if (sd.existsByUserName(student.getUserName())) {
            throw new RuntimeException("Username " + student.getUserName() + " already used");
        }

        student.setPasswordHash(PasswordUtil.hash(student.getPasswordHash()));
        sd.addStudent(student);
    }

    public List<Student> viewAllStudents() {
        return sd.getAllStudents();
    }

    public Student authenticate(String usernameOrEmail, String rawPassword) {
        Student student = sd.findByUsernameOrEmail(usernameOrEmail);
        if (student == null) {
            return null;
        }
        return PasswordUtil.matches(rawPassword, student.getPasswordHash()) ? student : null;
    }

}
