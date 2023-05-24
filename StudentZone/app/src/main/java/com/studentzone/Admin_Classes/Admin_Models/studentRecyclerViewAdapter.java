package com.studentzone.Admin_Classes.Admin_Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class studentRecyclerViewAdapter extends RecyclerView.Adapter<studentRecyclerViewAdapter.studentViewHolder> {
    ArrayList<Students> studentsArrayList;

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
//      holder.iv.setImageResource(student.getImage);
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
//        ImageView iv;
        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_student_name = itemView.findViewById(R.id.activity_admin_student_tv_sub_name);
            tv_student_aid = itemView.findViewById(R.id.activity_admin_student_tv_sub_code);
//            iv = itemView.findViewById(R.id.activity_admin_students_accounts_iv_m);
        }
    }
}
