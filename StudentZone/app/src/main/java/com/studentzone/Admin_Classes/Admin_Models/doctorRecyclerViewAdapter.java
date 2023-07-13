package com.studentzone.Admin_Classes.Admin_Models;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class doctorRecyclerViewAdapter extends RecyclerView.Adapter<doctorRecyclerViewAdapter.doctorViewHolder> {
    ArrayList<Doctors> doctorsArrayList;
    BottomSheetDialog bottomSheetDialog;
    View bottomSheetDialogView;
    EditText name, email, password, gender;
    My_DB db = null;
    Doctors doctor;
    AlertDialog.Builder builder;

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
         doctor  = doctorsArrayList.get(position);
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

                doctor = doctorsArrayList.get(holder.getAdapterPosition());

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
     class doctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView tv_doctor_name, tv_doctor_password;
        ImageView iv;
        ImageButton ib_more;

        public doctorViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_doctor_name = itemView.findViewById(R.id.activity_admin_doctor_tv_doctor_name);
            tv_doctor_password = itemView.findViewById(R.id.activity_admin_doctor_tv_doctor_code);
            iv = itemView.findViewById(R.id.activity_admin_doctor_iv);
            ib_more = itemView.findViewById(R.id.activity_admin_doctor_ibtn_more);

            ib_more.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
            db = new My_DB(v.getContext());
            builder = new AlertDialog.Builder((v.getContext()));
        }

        /** This Methode To Show The Option (Edite, Delete) When User Clicked On More
         **********************************************************************************************/
        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.models_ibtn_more_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();

        }

        /** This Methode To Do Action After User Choose Delete Or Edite
         * If User Chosen Was Delete Will Appear Confirmation Massage
         **********************************************************************************************/
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            doctor = doctorsArrayList.get(getAdapterPosition());

            builder.setTitle("Confirm Delete");
            builder.setMessage("Are you sure you want to delete this Doctor?");

            switch (item.getItemId()){

                case R.id.models_ibtn_menu_edit:

                    return true;

                case R.id.models_ibtn_menu_delete:
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteDoctor(doctor.getEmail());

                            doctorsArrayList.remove(getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return true;

                default:
                    return false;
            }
        }
    }
}
