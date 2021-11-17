package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    static  final private Model instance = new Model();
    private List<Student> data = new LinkedList<Student>();

    private Model(){
        for(int i=0; i<10;++i){
            Student student = new Student();
            student.setID("000" + i);
            student.setName("Kuku" + i);
            data.add(student);
        }
    }

    public List<Student> getStudentList(){
        return data;
    }

    public void addNewStudent(Student student){
        data.add(student);
    }

    public static Model getInstance(){
        return instance;
    }


}