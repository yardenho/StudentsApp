package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class EditStudent extends AppCompatActivity {
    List<Student> data;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        data= Model.getInstance().getStudentList();
        TextView nameTv = findViewById(R.id.editStudent_studentName_editText);
        TextView idTv = findViewById(R.id.editStudent_studentId_editText);
        CheckBox cbTv = findViewById(R.id.editStudent_checked_cb);
        TextView phoneTv = findViewById(R.id.editStudent_studentPhone_editText);
        TextView addressTv = findViewById(R.id.editstudent_address_editText);
        Button cancelBtn = findViewById(R.id.editStudent_cancel_btn);
        Button deleteBtn = findViewById(R.id.editStudent_delete_btn);
        Button saveBtn = findViewById(R.id.editStudent_save_btn);

        //לבדוק אם זה צריך להיות בonResume
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            position = extras.getInt("pos");

            Student student = data.get(position);
            nameTv.setText(student.getName());
            idTv.setText(student.getID());
            cbTv.setChecked(student.getcb());
            phoneTv.setText(student.getPhoneNumber());
            addressTv.setText(student.getAddress());
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                Intent intent = new Intent(EditStudent.this, StudentRecyclerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = data.get(position);
                student.setName(nameTv.getText().toString());
                student.setID(idTv.getText().toString());
                student.setPhoneNumber(phoneTv.getText().toString());
                student.setAddress(addressTv.getText().toString());
                student.setCB(cbTv.isChecked());
                finish();
            }
        });



    }
}