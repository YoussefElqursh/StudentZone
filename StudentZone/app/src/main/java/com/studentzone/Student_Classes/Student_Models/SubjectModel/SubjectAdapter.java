package com.studentzone.Student_Classes.Student_Models.SubjectModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Data_Base.Courses;
import com.studentzone.R;

import java.util.List;
import java.util.Locale;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder>{

    Context context ;
    List<SubjectModel>studentSubjectModel;

    public SubjectAdapter(Context context, List<SubjectModel> SubjectAdapter) {
        this.context = context;
        this.studentSubjectModel = SubjectAdapter;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_student_model_subject,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {

        SubjectModel course = studentSubjectModel.get(position);

        String  abbreviation = "",courseName = course.subject_name;
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }

        holder.subject_name.setText(studentSubjectModel.get(position).getSubject_name());
        holder.subject_description.setText(studentSubjectModel.get(position).getSubject_description());
        holder.tv_first_letter_of_course.setText(abbreviation.toUpperCase(Locale.ROOT));
    }

    @Override
    public int getItemCount() {
        return studentSubjectModel.size();
    }
}
