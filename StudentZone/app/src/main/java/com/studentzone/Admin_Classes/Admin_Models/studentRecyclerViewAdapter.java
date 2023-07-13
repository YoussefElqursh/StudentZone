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
import com.studentzone.Admin_Classes.Admin_Activities.AdminStudentsAccountsActivity;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class studentRecyclerViewAdapter extends RecyclerView.Adapter<studentRecyclerViewAdapter.studentViewHolder> {
    ArrayList<Students> studentsArrayList;
    BottomSheetDialog bottomSheetDialog;
    View bottomSheetDialogView = null;
    EditText name, aid, email, password, gender;
    Students student;
    My_DB db;
    AlertDialog.Builder builder;

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
        student = studentsArrayList.get(position);

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

                student = studentsArrayList.get(holder.getAdapterPosition());

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
     class studentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView tv_student_name,tv_student_aid;
        ImageView iv;
        ImageButton ib_more;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_student_name = itemView.findViewById(R.id.activity_admin_student_tv_sub_name);
            tv_student_aid = itemView.findViewById(R.id.activity_admin_student_tv_sub_code);
            iv = itemView.findViewById(R.id.activity_admin_student_iv);
            ib_more = itemView.findViewById(R.id.activity_admin_student_ibtn_more);

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

            student = studentsArrayList.get(getAdapterPosition());

            builder.setTitle("Confirm Delete");
            builder.setMessage("Are you sure you want to delete this student?");

            switch (item.getItemId()){

                case R.id.models_ibtn_menu_edit:

                    return true;

                case R.id.models_ibtn_menu_delete:

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteStudent(student.getEmail());

                            studentsArrayList.remove(getAdapterPosition());
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
