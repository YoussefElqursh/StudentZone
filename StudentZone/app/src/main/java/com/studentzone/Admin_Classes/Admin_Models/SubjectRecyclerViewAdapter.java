package com.studentzone.Admin_Classes.Admin_Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.studentzone.Data_Base.Courses;
import com.studentzone.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.subjectViewHolder>
{

    ArrayList<Courses> subjectsArrayList;

    public SubjectRecyclerViewAdapter(ArrayList<Courses> subjectsArrayList)
    {
        this.subjectsArrayList = subjectsArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public subjectViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_subject,null,false);
        SubjectRecyclerViewAdapter.subjectViewHolder subjectViewHolder = new SubjectRecyclerViewAdapter.subjectViewHolder(view);

        return subjectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SubjectRecyclerViewAdapter.subjectViewHolder holder, int position)
    {
        Courses course = subjectsArrayList.get(position);

        String  abbreviation = "",courseName = course.getName();
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }

        holder.tv_course_name.setText(course.getName());
        holder.tv_course_code.setText(course.getCode());
        holder.tv_first_letter_of_course.setText(abbreviation.toUpperCase(Locale.ROOT));

    }

    @Override
    public int getItemCount()
    {
        return subjectsArrayList.size();
    }

    class subjectViewHolder extends RecyclerView.ViewHolder{
        TextView tv_course_name, tv_course_code, tv_first_letter_of_course;
        public subjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_course_name = itemView.findViewById(R.id.activity_admin_subject_tv_sub_name);
            tv_course_code  = itemView.findViewById(R.id.activity_admin_subject_tv_sub_code);
            tv_first_letter_of_course  = itemView.findViewById(R.id.activity_admin_subject_tv_sn);
        }
    }
}
