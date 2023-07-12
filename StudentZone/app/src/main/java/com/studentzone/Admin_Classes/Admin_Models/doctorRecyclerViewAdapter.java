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
import com.studentzone.Data_Base.Doctors;
import com.studentzone.R;

import java.util.ArrayList;

public class doctorRecyclerViewAdapter extends RecyclerView.Adapter<doctorRecyclerViewAdapter.doctorViewHolder> {
    ArrayList<Doctors> doctorsArrayList;
    BottomSheetDialog bottomSheetDialog;
    View bottomSheetDialogView = null;
    EditText name, email, password, gender;



    public doctorRecyclerViewAdapter(ArrayList<Doctors> doctorsArrayList) {
        this.doctorsArrayList = doctorsArrayList;
    }

    @NonNull
    @Override
    public doctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_doctor,null,false);
        doctorViewHolder doctorViewHolder = new doctorViewHolder(view);
        return doctorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull doctorViewHolder holder, int position) {
         Doctors doctor  = doctorsArrayList.get(position);
         holder.tv_doctor_name.setText(doctor.getFName());
         holder.tv_doctor_password.setText(doctor.getPassword());

         if(doctor.getGender().equals("Male"))
             holder.iv.setImageResource(R.drawable.ic_male_doctor);
         else
             holder.iv.setImageResource(R.drawable.ic_female_doctor);


        /** This Method to show Details Of Doctor When User Clicked In Any Card View (any doctor)
         **********************************************************************************************/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.BottomSheetStyle);
                bottomSheetDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_admin_doctors_accouts_show,null);

                name = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_name);
                email = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_email);
                password = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_password);
                gender = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_gender);

                name.setText(doctor.getFName());
                email.setText(doctor.getEmail());
                password.setText(doctor.getPassword());
                gender.setText(doctor.getGender());

                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorsArrayList.size();
    }


    /** holder Class For doctorRecyclerViewAdapter
     **********************************************************************************************/
    static class doctorViewHolder extends RecyclerView.ViewHolder{

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
