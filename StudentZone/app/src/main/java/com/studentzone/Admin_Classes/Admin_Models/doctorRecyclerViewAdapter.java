package com.studentzone.Admin_Classes.Admin_Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.Data_Base.Doctors;
import com.studentzone.R;

import java.util.ArrayList;

public class doctorRecyclerViewAdapter extends RecyclerView.Adapter<doctorRecyclerViewAdapter.doctorViewHolder> {
    ArrayList<Doctors> doctorsArrayList;

    public doctorRecyclerViewAdapter(ArrayList<Doctors> doctorsArrayList) {
        this.doctorsArrayList = doctorsArrayList;
    }

    @NonNull
    @Override
    public doctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_doctor,null,false);
        doctorViewHolder doctorViewHolder = new doctorViewHolder (view);
        return doctorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull doctorViewHolder holder, int position) {
         Doctors doctor  = doctorsArrayList.get(position);
         holder.tv_doctor_name.setText(doctor.getFName());
         holder.tv_doctor_password.setText(doctor.getPassword());
//       holder.iv.setImageResource(doctor.getImage);

    }

    @Override
    public int getItemCount() {
        return doctorsArrayList.size();
    }


    /** holder Class For doctorRecyclerViewAdapter
     **********************************************************************************************/
    class doctorViewHolder extends RecyclerView.ViewHolder{

        TextView tv_doctor_name, tv_doctor_password;
        ImageView iv;
        public doctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_doctor_name = itemView.findViewById(R.id.activity_admin_doctor_tv_doctor_name);
            tv_doctor_password = itemView.findViewById(R.id.activity_admin_doctor_tv_doctor_code);
            iv = itemView.findViewById(R.id.activity_admin_doctor_iv);
        }
    }
}
