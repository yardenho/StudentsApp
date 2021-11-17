package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class
Present_student_details extends AppCompatActivity {
    List<Student> data;
    int position;
    TextView nameTv;
    TextView idTv;
    CheckBox cbTv;
    TextView phoneTv;
    TextView addressTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_student_details);
    }

    private void setDetails() {
        Student student = data.get(position);
        nameTv.setText(student.getName());
        idTv.setText(student.getID());
        cbTv.setChecked(student.getcb());
        phoneTv.setText(student.getPhoneNumber());
        addressTv.setText(student.getAddress());
    }

    @Override
    protected void onResume() {
        super.onResume();
        data= Model.getInstance().getStudentList();
        nameTv = findViewById(R.id.present_details_edit_name);
        idTv = findViewById(R.id.present_details_id_edit);
        cbTv = findViewById(R.id.present_details_cb);
        phoneTv = findViewById(R.id.present_details_phone_edit);
        addressTv = findViewById(R.id.present_details_address_edit);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            position = extras.getInt("pos");
            setDetails();
        }

        Button editBtn = findViewById(R.id.present_details_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Present_student_details.this, EditStudent.class);
                intent.putExtra("pos", position); // THE PROBLEMMMMM
                startActivity(intent);
            }
        });
    }
}