package com.example.studentsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.class4demo.model.Model;
import com.example.class4demo.model.Student;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Student> data = new LinkedList<Student>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = Model.instance.getStudentList();
        ListView listV = findViewById(R.id.main_list_view);
        MyAdapter adapter = new MyAdapter();
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "row selscted: " + position);
            }
        });


    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.student_list_row, null);
                CheckBox cb = convertView.findViewById(R.id.list_rpw_checkBox);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Tag", "cb " + cb.getTag().toString());
                        int pos = Integer.parseInt(cb.getTag().toString());
                        Student student = data.get(pos);
                        student.cb = cb.isChecked();
                    }
                });
            }
            TextView nameTv = convertView.findViewById(R.id.list_row_name_tv);
            TextView idTv = convertView.findViewById(R.id.list_row_id_tv);
            CheckBox cb = convertView.findViewById(R.id.list_rpw_checkBox);
            cb.setTag(position);

            Student student = data.get(position);
            nameTv.setText(student.name);
            idTv.setText(student.id);
            cb.setChecked(student.cb);

            return convertView;
        }
    }
}