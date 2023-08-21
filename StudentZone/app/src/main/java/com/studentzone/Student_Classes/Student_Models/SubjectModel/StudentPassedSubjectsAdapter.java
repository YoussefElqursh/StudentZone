package com.studentzone.Student_Classes.Student_Models.SubjectModel;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Fragments.FristLevelFragment;

import java.util.ArrayList;
import java.util.Locale;

public class StudentPassedSubjectsAdapter extends RecyclerView.Adapter<StudentPassedSubjectsAdapter.ViewHolder> {

    Button btn_back;
    Context context;
    My_DB Db;
    ArrayList<StudentPassedModel>arrayList=new ArrayList<>(); //array list



    public StudentPassedSubjectsAdapter() {

    }


    public StudentPassedSubjectsAdapter(Context context, ArrayList<StudentPassedModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        Db = new My_DB(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_student_model_passed_subject,parent,false);//holder have model
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1_n.setText(arrayList.get(position).getSubjectName());
        holder.tv2_s.setText(Integer.toString(arrayList.get(position).getSubjectScore()));
        holder.tv4_g.setText(arrayList.get(position).getgrade());


        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.itemView.startAnimation(animation);

        StudentPassedModel model = arrayList.get(position);

        String  abbreviation = "",courseName = model.getSubjectName();
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }
        holder.tv3_a.setText(abbreviation.toUpperCase(Locale.ROOT));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv1_n,tv2_s,tv3_a,tv4_g;
        CardView crv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv4_g=itemView.findViewById(R.id.activity_student_passed_subject_ibtn_grade);//store inflate in object
            tv2_s=itemView.findViewById(R.id.activity_student_passed_subject_tv_score);
            tv3_a=itemView.findViewById(R.id.activity_student_passed_subject_tv_sn);
            crv=itemView.findViewById(R.id.activity_student_Regestration_cv);
            tv1_n=itemView.findViewById(R.id.activity_student_passed_subject_tv_sub_name);


        }
    }
}