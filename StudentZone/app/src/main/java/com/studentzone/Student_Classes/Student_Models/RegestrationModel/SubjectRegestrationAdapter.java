package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Activities.StudentSubjectActivity;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;
import java.util.Locale;

public class SubjectRegestrationAdapter extends RecyclerView.Adapter<SubjectRegestrationAdapter.ViewHolder> {
    ArrayList<SubjectRegestrationModel>arrayList=new ArrayList<>(); //array list
    ArrayList<SubjectRegestrationModel>arrayList_Enrollment=new ArrayList<>(); //array list
    private SharedPreferences sharedPreferences;


    Context context;
    CardView cv_subjects;
    public SubjectRegestrationAdapter(Context context, ArrayList<SubjectRegestrationModel>arrayList){

        this.context=context;
        this.arrayList=arrayList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_student_model_regestration,parent,false);//holder have model
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        holder.tv1_n.setText(arrayList.get(position).getSubjectName());
        holder.tv2_c.setText(arrayList.get(position).getCodeName());
        SubjectRegestrationModel model = arrayList.get(position);

        String  abbreviation = "",courseName = model.getSubjectName();
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }
        holder.tv_3.setText(abbreviation.toUpperCase(Locale.ROOT));

        holder.CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int checkboxPosition = holder.getAdapterPosition();
               SubjectRegestrationModel model = arrayList.get(checkboxPosition);
                System.out.println("position"+checkboxPosition);
                model.setCheckBox(isChecked);

                // Create a new ArrayList to store the checked subjects
                 ArrayList<SubjectRegestrationModel> checkedSubjects = new ArrayList<>();

                // Iterate over the items in the arrayList_Enrollment and add the checked items to the checkedSubjects list
                for (int i = 0; i < arrayList.size(); i++) {
                    SubjectRegestrationModel item = arrayList.get(i);
                    if (item.isCheckBox()) {
                        checkedSubjects.add(item);
                    }
                }
//                Intent intent = new Intent(context, StudentSubjectActivity.class);
//                intent.putExtra("checkedSubjects", checkedSubjects);
//                context.startActivity(intent);
              






            }
        });
    }










    @Override
    public int getItemCount() {
        return arrayList.size();
    }//Adapter
    public class ViewHolder extends RecyclerView.ViewHolder {//holder
        ImageButton IM;
        TextView tv1_n,tv2_c,tv_3;
        CheckBox CB;
        CardView crv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1_n=itemView.findViewById(R.id.activity_student_Regestration_tv_sub_name);//store inflate in object
            tv2_c=itemView.findViewById(R.id.activity_student_Regestration_tv_sub_code);
            crv=itemView.findViewById(R.id.activity_student_Regestration_cv);
            CB=itemView.findViewById(R.id.activity_student_subject_tv_sub_state);
            tv_3=itemView.findViewById(R.id.activity_student_Regestration_tv_sn);
            IM=itemView.findViewById(R.id.activity_student_Regestration_ibtn_info);


        }
    }

}
