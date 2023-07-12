package com.studentzone.Admin_Classes.Admin_Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Activities.AdminStudentsAccountsActivity;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class studentRecyclerViewAdapter extends RecyclerView.Adapter<studentRecyclerViewAdapter.studentViewHolder> {
    ArrayList<Students> studentsArrayList;
    BottomSheetDialog bottomSheetDialog;
    View bottomSheetDialogView = null;
    EditText name, aid, email, password, gender;

    public studentRecyclerViewAdapter(ArrayList<Students> studentsArrayList) {
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_student,null,false);
        studentViewHolder studentViewHolder = new studentViewHolder(view);

        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {
        Students student = studentsArrayList.get(position);

        holder.tv_student_name.setText(student.getFName());
        holder.tv_student_aid.setText(student.getAcademic_Number());

        if(student.getGender().equals("Male"))
            holder.iv.setImageResource(R.drawable.ic_male_student);
        else
            holder.iv.setImageResource(R.drawable.ic_female_student);

        /**This Method to show Details Of Student When User Clicked In Any Card View (any student)
         **********************************************************************************************/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.BottomSheetStyle);
                bottomSheetDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_admin_students_accouts_show,null);

                name = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_name);
                aid =  bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_aid);
                email = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_email);
                password = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_password);
                gender = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_gender);

                name.setText(student.getFName());
                aid.setText(student.getAcademic_Number());
                email.setText(student.getEmail());
                password.setText(student.getPassword());
                gender.setText(student.getGender());

                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

    public void addItem(Students student){
        studentsArrayList.add(student);
    }


    /** holder Class For studentRecyclerViewAdapter
     **********************************************************************************************/
     class studentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_student_name,tv_student_aid;
        ImageView iv;
        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_student_name = itemView.findViewById(R.id.activity_admin_student_tv_sub_name);
            tv_student_aid = itemView.findViewById(R.id.activity_admin_student_tv_sub_code);
            iv = itemView.findViewById(R.id.activity_admin_student_iv);
        }
    }
}
