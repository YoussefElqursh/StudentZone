package com.studentzone.Admin_Classes.Admin_Models;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Activities.AdminStudentsAccountsActivity;
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class studentRecyclerViewAdapter extends RecyclerView.Adapter<studentRecyclerViewAdapter.studentViewHolder> {
    private ArrayList<Students> studentsArrayList;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;
    private EditText name, aid, email, password, gender;
    Students student;
    private My_DB db;

    private Button btn_save, btn_close;
    private RadioGroup rg;
    private RadioButton rb_male , rb_female;
    private AlertDialog.Builder builder;

    public studentRecyclerViewAdapter(Context context, ArrayList<Students> studentsArrayList) {
        this.studentsArrayList = studentsArrayList;
        this.db = new My_DB(context);
        this.bottomSheetDialog = new BottomSheetDialog(context);
        this.builder = new AlertDialog.Builder(context);
    }

    /** onCreateViewHolder ()
     *  This method inflates the item layout for the doctor and returns a new instance of the doctorViewHolder class.
     **********************************************************************************************/
    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_student,null,false);
        studentViewHolder studentViewHolder = new studentViewHolder(view);

        return studentViewHolder;
    }


    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the doctor's data to the view holder.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {

        student = studentsArrayList.get(position);
        holder.bind(student);
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


        public void bind(Students student) {

            tv_student_name.setText(student.getFName());
            tv_student_aid.setText(student.getAcademic_Number());

            if(student.getGender() != null  && student.getGender().equals("Male"))
                iv.setImageResource(R.drawable.ic_male_student);
            else
                iv.setImageResource(R.drawable.ic_female_student);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showStudentDetailsDialog(student);
                }
            });
        }

        public void onClick(View v) {
            showPopupMenu(v);
        }

        /** showPopupMenu ()
         *  This method that shows a popup menu with the options to edit or delete a doctor ,
         *  when the user clicks on the more button in the doctor's card view.
         *  **********************************************************************************************/
        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.models_ibtn_more_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();

        }

        /** onMenuItemClick()
         *  This method that handles the click event of the popup menu items (edit or delete).
         **********************************************************************************************/
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Students student = studentsArrayList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    showEditConfirmationDialog(student);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    showDeleteConfirmationDialog(student);
                    return true;
                default:
                    return false;
            }
        }

        /** showStudentDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a student when the user clicks on the student's card view.
         *  It inflates a layout for displaying the details of a student and sets the text of the views in the layout to the corresponding data of the student.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void showStudentDetailsDialog(Students student) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_students_accouts_show, null, false);

            name = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_name);
            email = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_email);
            password = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_password);
            gender = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_gender);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_show_student_btn_close);

            name.setText(student.getFName());
            email.setText(student.getEmail());
            password.setText(student.getPassword());
            gender.setText(student.getGender());

            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

        /**showEditConfirmationDialog ()
         * method shows a confirmation dialog for editing a student's information when the user clicks on the edit option in the popup menu.
         * It sets the message of the dialog and sets click listeners on the positive and negative buttons.
         * If the user clicks the positive button, the showEditStudentDialog method is called.
         **********************************************************************************************/
        private void showEditConfirmationDialog(Students student) {
            builder.setMessage("Are you sure you want to edit this student's information?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            showEditStudentDialog(student);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        /**showEditStudentDialog ()
         * method shows a bottom sheet dialog for editing a student's information.
         * It inflates a layout for editing a student's information and sets the text of the views in the layout to the corresponding data of the student.
         * It also sets click listeners on the save and close buttons.
         * If the user clicks the save button, the data of the student is updated, and the updateStudent method is called on the My_DB object to update the student's information in the database.
         * The student's data in the studentsArrayList is also updated, and the RecyclerView is notified of the change.
         **********************************************************************************************/
        private void showEditStudentDialog(Students student) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_students_accouts_edit, null, false);

            name = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_name);
            email = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_email);
            password = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_password);
            btn_save = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_btn_save);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_btn_close);

            rg = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rg_student_kind);
            rb_male = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rb_male);
            rb_female = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rb_female);

            name.setText(student.getFName());
            email.setText(student.getEmail());
            password.setText(student.getPassword());

            if (student.getGender().equals("Male") && student.getGender() != null) {
                rb_male.setChecked(true);
            } else if (student.getGender().equals("Female") && student.getGender() != null) {
                rb_female.setChecked(true);
            }

            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String gender = "";
                    if (rb_male.isChecked()) {
                        gender = "Male";
                    } else if (rb_female.isChecked()) {
                        gender = "Female";
                    }

                    student.setFName(name.getText().toString());
                    student.setEmail(email.getText().toString());
                    student.setPassword(password.getText().toString());
                    student.setGender(gender);

                    if (student.getGender() != null && student.getGender().equals("Male")) {
                        iv.setImageResource(R.drawable.ic_male_student);
                    } else {
                        iv.setImageResource(R.drawable.ic_female_student);
                    }

                    db.updateStudent(student);

                    studentsArrayList.set(getAdapterPosition(), student);
                    notifyItemChanged(getAdapterPosition());

                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }


        private void showDeleteConfirmationDialog(Students student) {
            builder.setMessage("Are you sure you want to delete this student's information?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            db.deleteDoctor(student.getEmail());
                            studentsArrayList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
