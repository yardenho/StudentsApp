package com.example.studentsapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentRecyclerActivity extends AppCompatActivity {
    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler);

        ImageButton addBtn = findViewById(R.id.add_student_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentRecyclerActivity.this, NewStudent.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView list = findViewById(R.id.student_recycler_rview);
        list.setHasFixedSize(true);

        //connecting the layout manager -מסדר את התצוגה
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        data = Model.getInstance().getStudentList();

        //נחבר את האקטיביטי עם האדפטר
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //כאן צריך לפתוח את האקטיביטי של הצגת פרטים לפי המיקום הספציפי
                Intent intent = new Intent(StudentRecyclerActivity.this, Present_student_details.class);
                intent.putExtra("pos", position);
                startActivity(intent);
                Log.d("TAG", "row was clicked " + position);
            }
        });

        adapter.setOnCbClickListener(new OnCbClickListener() {
            @Override
            public void OnCbClick(int position) {
                Student student = data.get(position);
                Log.d("TAG", "the cb:" +!student.getcb());
                student.setCB(!student.getcb());
                Log.d("TAG", "checkBox was clicked "+ position);
            }
        });

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final OnItemClickListener listener;
        private final OnCbClickListener cbListener;
        //תפקידו לזכור את הרפרנסים
        TextView name;
        TextView id;
        CheckBox cb;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener, OnCbClickListener cbListener) {
            super(itemView);

            name = itemView.findViewById(R.id.list_row_name_tv);
            id = itemView.findViewById(R.id.list_row_id_tv);
            cb = itemView.findViewById(R.id.list_rpw_checkBox);
            this.listener = listener;
            this.cbListener = cbListener;

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
                    cbListener.OnCbClick(pos);
                }
            });
        }

        public void bind(Student student){
            name.setText(student.getName());
            id.setText(student.getID());
            cb.setChecked(student.getcb());
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public interface OnCbClickListener{
        void OnCbClick(int position);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private OnItemClickListener listener;
        private OnCbClickListener cbListener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        void setOnCbClickListener(OnCbClickListener cbListener){
            this.cbListener = cbListener;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //יוצר אובייקט חדש מהשורה
            LayoutInflater inflater = getLayoutInflater();
            View rowView = inflater.inflate(R.layout.student_list_row, parent,false);
            //יצירת הולדר שיעטוף אותו
            MyViewHolder viewHolder = new MyViewHolder(rowView, listener, cbListener);
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