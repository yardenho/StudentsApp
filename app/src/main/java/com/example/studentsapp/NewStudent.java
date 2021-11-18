package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class NewStudent extends AppCompatActivity {
    private List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button saveBtn = findViewById(R.id.newStudent_save_btn);
        Button cancelBtn  = findViewById(R.id.newStudent_cancel_btn);
        EditText nameTv = findViewById(R.id.newStudent_name_edit);
        EditText idTv = findViewById(R.id.newStudent_id_edit);
        EditText phoneTv = findViewById(R.id.newStudent_phone_edit);
        EditText addressTv = findViewById(R.id.newStudent_address_edit);
        CheckBox cb = findViewById(R.id.newStudent_cb);

        data = Model.getInstance().getStudentList();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student= new Student();
                student.setName(nameTv.getText().toString());
                student.setID(idTv.getText().toString());
                student.setPhoneNumber(phoneTv.getText().toString());
                student.setAddress(addressTv.getText().toString());
                student.setCB(cb.isChecked());
                data.add(student);
                finish();
            }
        });



    }
}