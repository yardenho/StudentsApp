package com.example.studentsapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.class4demo.model.Model;
import com.example.class4demo.model.Student;

import java.util.List;

public class StudentRecyclerActivity extends AppCompatActivity {
    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler);
        //the list
        RecyclerView list = findViewById(R.id.student_recycler_rview);
        list.setHasFixedSize(true);

        //connecting the layout manager -מסדר את התצוגה
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        data = Model.instance.getStudentList();

        //נחבר את האקטיביטי עם האדפטר
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListenre() {
            @Override
            public void OnItemClick(int position) {
                Log.d("TAG", "row was clicked " + position);
            }
        });

    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final OnItemClickListenre listener;
        //תפקידו לזכור את הרפרנסים
        TextView name;
        TextView id;
        CheckBox cb;
        public MyViewHolder(@NonNull View itemView, OnItemClickListenre listener) {
            super(itemView);


            name = itemView.findViewById(R.id.list_row_name_tv);
            id = itemView.findViewById(R.id.list_row_id_tv);
            cb = itemView.findViewById(R.id.list_rpw_checkBox);
            this.listener = listener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.OnItemClick(pos);
                }
            });

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Log.d("TAG", "checkBox was clicked "+ pos);

                }
            });
        }

        public void bind(Student student){
            name.setText(student.name);
            id.setText(student.id);
            cb.setChecked(student.cb);
        }
    }

    public interface OnItemClickListenre{
        void OnItemClick(int position);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private OnItemClickListenre listener;

        void setOnItemClickListener(OnItemClickListenre listenre){
            this.listener = listenre;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //יוצר אובייקט חדש מהשורה
            LayoutInflater inflater = getLayoutInflater();
            View rowView = inflater.inflate(R.layout.student_list_row, parent,false);
            //יצירת הולדר שיעטוף אותו
            MyViewHolder viewHolder = new MyViewHolder(rowView, listener);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //תחבר לי את הview עם הdata של אותה שורה
            Student student = data.get(position);
            holder.bind(student);


        }

        @Override
        public int getItemCount() {
            //כמה שורות יש לי בליסט
            return data.size();
        }
    }
}