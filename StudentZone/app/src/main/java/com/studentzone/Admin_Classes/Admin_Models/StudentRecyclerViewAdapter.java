package com.studentzone.Admin_Classes.Admin_Models;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.studentViewHolder> {
    private ArrayList<Students> studentList;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;
    private EditText studentName, studentAID, studentEmail, studentPassword, studentGender;
    Students student;
    private My_DB db;
    private Button btn_save, btn_close;
    private RadioGroup rg;
    private RadioButton rb_male , rb_female;
    private AlertDialog.Builder builder;

    public StudentRecyclerViewAdapter(Context context, ArrayList<Students> studentList) {
        this.studentList = studentList;
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

        student = studentList.get(position);
        holder.setStudentData(student);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void addItem(Students student){
        studentList.add(student);
    }


    /** holder Class For studentRecyclerViewAdapter
     *  It holds references to the views in the item layout and binds the data to the views.
     **********************************************************************************************/
     class studentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView tv_student_name,tv_student_aid;
        ImageView iv;
        ImageButton ib_more;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_student_name = itemView.findViewById(R.id.activity_admin_student_tv_name);
            tv_student_aid = itemView.findViewById(R.id.activity_admin_student_tv_aid);
            iv = itemView.findViewById(R.id.activity_admin_student_iv);
            ib_more = itemView.findViewById(R.id.activity_admin_student_ibtn_more);

            ib_more.setOnClickListener(this);
        }


        /** setStudentData()
         * Sets the data for a student by populating the appropriate views with the student's information.
         * Additionally, it assigns an onClickListener to the itemView to display detailed student information when clicked.
         **********************************************************************************************/

        public void setStudentData(Students student) {

            tv_student_name.setText(student.getFName());
            tv_student_aid.setText(student.getAcademic_Number());

            if(student.getGender() != null  && student.getGender().equals("Male"))
                iv.setImageResource(R.drawable.ic_male_student);
            else
                iv.setImageResource(R.drawable.ic_female_student);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayStudentDetailsDialog(student);
                }
            });
        }

        public void onClick(View v) {
            displayPopupMenu(v);
        }

        /** displayPopupMenu ()
         *  This method that shows a popup menu with the options to edit or delete a student ,
         *  when the user clicks on the more button in the student's card view.
         *  **********************************************************************************************/
        private void displayPopupMenu(View view){
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
            Students student = studentList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    displayEditStudentDialog(student);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    displayDeleteConfirmationDialog(student);
                    return true;
                default:
                    return false;
            }
        }

        /** displayStudentDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a student when the user clicks on the student's card view.
         *  It inflates a layout for displaying the details of a student and sets the text of the views in the layout to the corresponding data of the student.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void displayStudentDetailsDialog(Students student) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_students_accouts_show, null, false);

            studentName = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_name);
            studentAID = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_aid);
            studentEmail = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_email);
            studentPassword = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_password);
            studentGender = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_gender);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_show_student_btn_close);

            studentName.setText(student.getFName());
            studentAID.setText(student.getAcademic_Number());
            studentEmail.setText(student.getEmail());
            studentPassword.setText(student.getPassword());
            studentGender.setText(student.getGender());

            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

        /**displayEditStudentDialog ()
         * method shows a bottom sheet dialog for editing a student's information.
         * It inflates a layout for editing a student's information and sets the text of the views in the layout to the corresponding data of the student.
         * It also sets click listeners on the save and close buttons.
         * If the user clicks the save button, the data of the student is updated, and the updateStudent method is called on the My_DB object to update the student's information in the database.
         * The student's data in the studentsArrayList is also updated, and the RecyclerView is notified of the change.
         **********************************************************************************************/
        private void displayEditStudentDialog(Students student) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_students_accouts_edit, null, false);

            studentName = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_name);
            studentAID = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_aid);
            studentEmail = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_email);
            studentPassword = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_password);
            btn_save = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_btn_save);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_btn_close);

            rg = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rg_student_kind);
            rb_male = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rb_male);
            rb_female = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rb_female);

            //This Lines To FillOut Text Fields With Student Data
            studentName.setText(student.getFName());
            studentAID.setText(student.getAcademic_Number());
            studentEmail.setText(student.getEmail());
            studentPassword.setText(student.getPassword());


            //This Lines To Check Radio Button Which Detect The Gender Of Student
            if (student.getGender().equals("Male") && student.getGender() != null) {
                rb_male.setChecked(true);
            } else if (student.getGender().equals("Female") && student.getGender() != null) {
                rb_female.setChecked(true);
            }

            //We Will Put it disabled until user edit any data of this student
            btn_save.setEnabled(false);

            // Keep track of the original student name , email , aid , pass
            String originalName = student.getFName();
            String originalEmail = student.getEmail();
            String originalAcademicNumber = student.getAcademic_Number();
            String originalPassword = student.getPassword();

            /*
             *  textWatcher monitor changes in the name and password fields of a form.
             *  Whenever any of these fields are modified, the afterTextChanged method of textWatcher is called,
             * which enables the btn_save button.
             * */
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    // Enable the save button if the text has changed from the original values
                    if (!(studentName.getText().toString().equals(originalName) && studentPassword.getText().toString().equals(originalPassword) )) {
                        btn_save.setEnabled(true);
                    } else {
                        btn_save.setEnabled(false);
                    };
                }
            };

            /*
             * The addTextChangedListener method is then called on each of the relevant EditText fields with textWatcher as the argument.
             * This sets up the TextWatcher instance to monitor changes to each field.
             **/
            studentName.addTextChangedListener(textWatcher);
            studentPassword.addTextChangedListener(textWatcher);


            // This lines Add listener to the radio group to enable the save button when the user changes the gender
            rg.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == rb_male.getId()) {
                    if (!student.getGender().equals("Male") || !(studentName.getText().toString().equals(originalName) && studentPassword.getText().toString().equals(originalPassword) )) {
                        btn_save.setEnabled(true);
                    }
                    else {
                        btn_save.setEnabled(false);
                    }
                } else if (checkedId == rb_female.getId()) {
                    if (!student.getGender().equals("Female") || !(studentName.getText().toString().equals(originalName) && studentPassword.getText().toString().equals(originalPassword) )) {
                        btn_save.setEnabled(true);
                    }
                    else {
                        btn_save.setEnabled(false);
                    }
                }
            });



            //This is the action of close the bottomSheetDialogView of edit student
            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            //This is the action of save the changed data or edited data  of student
            btn_save.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //This lines to remember the user to enter data in student name and pass
                    if (TextUtils.isEmpty(studentName.getText().toString().trim())) {
                        studentName.setError("Is Required !");
                        return;
                    }
                    if (TextUtils.isEmpty(studentPassword.getText().toString().trim())) {
                        studentPassword.setError("Is Required !");
                        return;
                    }

                    //This lines to send edited student to data base across pass new instance of student to db.updateStudent
                    String gender = "";
                    if (rb_male.isChecked()) {
                        gender = "Male";
                    } else if (rb_female.isChecked()) {
                        gender = "Female";
                    }

                    student.setFName(studentName.getText().toString());
                    student.setEmail(studentEmail.getText().toString());
                    student.setPassword(studentPassword.getText().toString());
                    student.setGender(gender);

                    db.updateStudent(student);

                    //this lines to change the student icon if user changed it
                    if (student.getGender() != null && student.getGender().equals("Male")) {
                        iv.setImageResource(R.drawable.ic_male_student);
                    } else {
                        iv.setImageResource(R.drawable.ic_female_student);
                    }

                    studentList.set(getAdapterPosition(), student);
                    notifyItemChanged(getAdapterPosition());

                    bottomSheetDialog.dismiss();

                    Toast.makeText(bottomSheetDialog.getContext(), "Changes saved." , Toast.LENGTH_SHORT).show();

                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }



        /** displayDeleteConfirmationDialog()
         *  to show a confirmation dialog to the user before deleting a student.
         **********************************************************************************************/
        private void displayDeleteConfirmationDialog(Students student) {
            builder.setMessage("Are you sure you want to delete this student?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            db.deleteStudent(student.getEmail());
                            studentList.remove(getAdapterPosition());
                            Toast.makeText(bottomSheetDialog.getContext(), ""+student.getFName()+" Successfully Deleted." , Toast.LENGTH_SHORT).show();

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
