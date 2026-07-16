package com.ejaisoft.service;

import com.ejaisoft.dao.StudentDao;
import com.ejaisoft.model.Student;

import java.util.List;

public class StudentService {
    StudentDao sd = new StudentDao();

    public void onboardStudent(Student student){
        //Student exists?
        if(sd.existsByAdmissionNumber(student.getRegNumber())){
            throw new RuntimeException("Student with admission "+student.getRegNumber() +" exists");
        }
        if(sd.existsByEmail(student.getEmail())){
            throw new RuntimeException("Email " + student.getEmail() + " already exists");
        }
        if (sd.existsByUserName(student.getUserName())) {
            throw new RuntimeException("Username " + student.getUserName() + " already used");
        }

        sd.addStudent(student);
    }

    public List<Student> viewAllStudents(){
        return sd.getAllStudents();
    }





}
