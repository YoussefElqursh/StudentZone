package com.studentzone.Admin_Classes.Admin_Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class subjectRecyclerViewAdapter extends RecyclerView.Adapter<subjectRecyclerViewAdapter.subjectViewHolder>
{

    ArrayList<Courses> subjectsArrayList;

    public subjectRecyclerViewAdapter(ArrayList<Courses> subjectsArrayList)
    {
        this.subjectsArrayList = subjectsArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public subjectViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_subject,null,false);
        subjectRecyclerViewAdapter.subjectViewHolder subjectViewHolder = new subjectRecyclerViewAdapter.subjectViewHolder(view);

        return subjectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull subjectRecyclerViewAdapter.subjectViewHolder holder, int position)
    {
        Courses course = subjectsArrayList.get(position);

        holder.tv_course_name.setText(course.getName());
        holder.tv_course_code.setText(course.getCode());
//      holder.iv.setImageResource(student.getImage);

    }

    @Override
    public int getItemCount()
    {
        return subjectsArrayList.size();
    }

    class subjectViewHolder extends RecyclerView.ViewHolder{
        TextView tv_course_name, tv_course_code;
        public subjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_course_name = itemView.findViewById(R.id.activity_admin_subject_tv_sub_name);
            tv_course_code  = itemView.findViewById(R.id.activity_admin_subject_tv_sub_code);
        }
    }
}
