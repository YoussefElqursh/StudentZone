package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

//public class SubjectRegestrationViewHolder extends RecyclerView.ViewHolder {
//
//    TextView subject_name;
//    CheckBox checkBox ;
//
//    public SubjectRegestrationViewHolder(@NonNull View itemView) {
//
//        super(itemView);
//
//        subject_name =itemView.findViewById(R.id.activity_student_Regestration_tv_sub_name);
//        checkBox =itemView.findViewById(R.id.activity_student_subject_tv_sub_state);
//
//    }
//}

public class SubjectRegestrationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView subject_name;
    CheckBox checkBox;
    Context context;
    EditText et_emil,password;

    public SubjectRegestrationViewHolder(@NonNull View itemView) {

        super(itemView);

        subject_name = itemView.findViewById(R.id.activity_student_Regestration_tv_sub_name);
        checkBox = itemView.findViewById(R.id.activity_student_subject_tv_sub_state);

        // Set click listener on the itemView
        itemView.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        // Get the name of the subject from the TextView
        String subjectName = subject_name.getText().toString();


        My_DB database = new My_DB(context);
               database.insertEnrollment(subjectName);
              database.close();


    }
}


//public class SubjectRegestrationViewHolder extends RecyclerView.ViewHolder {
//
//    TextView subject_name;
//    CheckBox checkBox ;
//    Context context;
//
//    public SubjectRegestrationViewHolder(@NonNull View itemView) {
//
//        super(itemView);
//
//        subject_name =itemView.findViewById(R.id.activity_student_Regestration_tv_sub_name);
//        checkBox =itemView.findViewById(R.id.activity_student_subject_tv_sub_state);
//        context = itemView.getContext();
//
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = getAdapterPosition();
//                String subjectName = SubjectRegestrationModel.get(position).getSubject_name();
//
//                // Insert the subject name into the database
//                My_DB database = new My_DB(context);
//                database.insertSubject(subjectName);
//                database.close();
//            }
//        });
//    }
//}