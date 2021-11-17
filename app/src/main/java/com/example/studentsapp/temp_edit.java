package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class temp_edit extends AppCompatActivity {
    EditText nameEt;
    EditText idEt;
    EditText ageEt;
    CheckBox paidEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_edit);

        nameEt = findViewById(R.id);
        idEt = findViewById(R.id.main_id_edit_text);
        ageEt = findViewById(R.id.main_age_edit_text2);
        paidEt = findViewById(R.id.main_paid);


        Button saveBtn = findViewById(R.id.main_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

    }

    private void save() {
        String name = nameEt.getText().toString();
        String id = idEt.getText().toString();
        String age = ageEt.getText().toString();
        boolean paid = paidEt.isChecked();

        Log.d("TAG", "Student:" +name+ " "+age);

    }
}
}