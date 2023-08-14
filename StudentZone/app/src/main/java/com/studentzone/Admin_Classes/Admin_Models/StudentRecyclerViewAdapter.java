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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class  StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.studentViewHolder> {
    private ArrayList<Students> studentList;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;
    private EditText studentName, studentAID, studentEmail, studentPassword, studentGender,studentPhone,studentDept;
    private Students student;
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

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.itemView.startAnimation(animation);
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
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_show_student_accout, null, false);

            studentName = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_name);
            studentAID = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_aid);
            studentDept = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_department);
            studentEmail = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_email);
            studentPassword = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_password);
            studentGender = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_gender);
            studentPhone = bottomSheetDialogView.findViewById(R.id.fragment_show_student_et_student_phone);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_show_student_btn_close);

            studentName.setText(student.getFName());
            studentAID.setText(student.getAcademic_Number());
            studentEmail.setText(student.getEmail());
            studentPassword.setText(student.getPassword());
            studentGender.setText(student.getGender());
            studentPhone.setText(student.getPhone());
            studentDept.setText(db.getDepartmentNameById(student.getDept()));
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
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_edit_student_account, null, false);

            studentName = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_name);
            studentAID = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_aid);
            studentEmail = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_email);
            studentPassword = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_password);
            studentPhone = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_et_student_phone);
            SearchableSpinner departmentSpinner = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_sp_department);

            btn_save = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_btn_save);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_btn_close);

            rg = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rg_student_kind);
            rb_male = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rb_male);
            rb_female = bottomSheetDialogView.findViewById(R.id.fragment_edit_student_rb_female);

            /**Fills out the  spinner with data from the database.
             **********************************************************************************************/
            List<String> deptNames = db.getAllDepartmentsNames();

            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, deptNames);
            arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(arrayAdapter1);
            // Keep track of the original student name , email , aid , pass
            String originalName = student.getFName();
            String originalPassword = student.getPassword();
            String originalPhone = student.getPhone();
            String originalDept = db.getDepartmentNameById(student.getDept());

            //This Lines To FillOut Text Fields With Student Data
            studentName.setText(student.getFName());
            studentAID.setText(student.getAcademic_Number());
            studentEmail.setText(student.getEmail());
            studentPassword.setText(student.getPassword());
            studentPhone.setText(student.getPhone());
            selectItemFromSpinner(originalDept, departmentSpinner);



            //This Lines To Check Radio Button Which Detect The Gender Of Student
            if (student.getGender().equals("Male") && student.getGender() != null) {
                rb_male.setChecked(true);
            } else if (student.getGender().equals("Female") && student.getGender() != null) {
                rb_female.setChecked(true);
            }

            //We Will Put it disabled until user edit any data of this student
            btn_save.setEnabled(false);



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
                    // Enable the save button if the text has changed from the original values || the gender is changed
                    boolean genderChanged = false;
                    if (rb_male.isChecked() && !student.getGender().equals("Male")) {
                        genderChanged = true;
                    } else if (rb_female.isChecked() && !student.getGender().equals("Female")) {
                        genderChanged = true;
                    }
                    boolean dataChanged = !departmentSpinner.getSelectedItem().equals(originalDept) || !(studentName.getText().toString().equals(originalName) && studentPassword.getText().toString().equals(originalPassword)&& studentPhone.getText().toString().equals(originalPhone));
                    btn_save.setEnabled(genderChanged || dataChanged);

                }
            };

            /*
             * The addTextChangedListener method is then called on each of the relevant EditText fields with textWatcher as the argument.
             * This sets up the TextWatcher instance to monitor changes to each field.
             **/
            studentName.addTextChangedListener(textWatcher);
            studentPassword.addTextChangedListener(textWatcher);
            studentPhone.addTextChangedListener(textWatcher);

            //is added to detect changes in the selected department.
            departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    boolean dataChanged = !departmentSpinner.getSelectedItem().equals(originalDept) || !(studentName.getText().toString().equals(originalName) && studentPassword.getText().toString().equals(originalPassword)&& studentPhone.getText().toString().equals(originalPhone));
                    btn_save.setEnabled( dataChanged);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            // This lines Add listener to the radio group to enable the save button when the user changes the gender
            rg.setOnCheckedChangeListener((group, checkedId) -> {
                boolean genderChanged = false;
                if (checkedId == rb_male.getId() && !student.getGender().equals("Male")) {
                    genderChanged = true;
                } else if (checkedId == rb_female.getId() && !student.getGender().equals("Female")) {
                    genderChanged = true;
                }
                boolean dataChanged = !(studentName.getText().toString().equals(originalName) && studentPassword.getText().toString().equals(originalPassword)&& studentPhone.getText().toString().equals(originalPhone));
                btn_save.setEnabled(genderChanged || dataChanged);
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

                    //This lines to remember the user to enter data in student name and password
                    if (TextUtils.isEmpty(studentName.getText().toString().trim())) {
                        studentName.setError("Is Required !");
                        return;
                    }
                    if (TextUtils.isEmpty(studentPassword.getText().toString().trim())) {
                        studentPassword.setError("Is Required !");
                        return;
                    }

                    if (TextUtils.isEmpty(studentPhone.getText().toString().trim()) || !studentPhone.getText().toString().trim().startsWith("01") || studentPhone.length()<11 || !android.util.Patterns.PHONE.matcher(studentPhone.getText().toString().trim()).matches()) {
                        studentPhone.setError("Please enter"+ "\n"+ "valid phone number!");
                        return;
                    }
                    //This lines to send edited student to data base across passing new instance of student to db.updateStudent
                    String gender = "";
                    if (rb_male.isChecked()) {
                        gender = "Male";
                    } else if (rb_female.isChecked()) {
                        gender = "Female";
                    }
                    //This lines to send edited Student to data base across pass new instance of student to db.updateStudent
                    student.setFName(studentName.getText().toString());
                    student.setEmail(studentEmail.getText().toString());
                    student.setPhone(studentPhone.getText().toString());
                    student.setPassword(studentPassword.getText().toString());
                    student.setDept(db.getDepartmentIdByName(departmentSpinner.getSelectedItem().toString()));
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
        /** selectItemFromSpinner()
         *   This method is used to select an item from a searchable spinner based on the provided itemToSelect.
         *   It iterates through the spinner items and selects the matching item
         **********************************************************************************************/
        private void selectItemFromSpinner(String itemToSelect, SearchableSpinner spinner) {
            if (itemToSelect != null) {
                for (int i = 0; i < spinner.getCount(); i++) {
                    if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(itemToSelect)) {
                        spinner.setSelection(i);
                        break;
                    }
                }

            }
        }
    }
}
