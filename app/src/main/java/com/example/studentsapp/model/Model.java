package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    static  final private Model instance = new Model();
    private List<Student> data = new LinkedList<Student>();

    private Model(){}

    public List<Student> getStudentList(){
        return data;
    }

    public void addNewStudent(Student student){
        data.add(student);
    }
    public void deleteStudent(int pos){
        data.remove(pos);
    }

    public static Model getInstance(){
        return instance;
    }


}