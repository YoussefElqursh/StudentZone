package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.StudentRecyclerViewAdapter;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

/**
 * Activity for managing students in the admin panel.
 */

public class AdminStudentsAccountsActivity extends AppCompatActivity {

    // Views
    private Button btn_add_student, btn_back, btn_save_student, btn_close_add_student_dialog;
   private EditText et_add_new_student_name, et_add_new_student_password, et_add_new_student_email, et_add_new_student_aid;
   private RadioGroup rg_gender;
   private BottomSheetDialog addStudentBottomSheetDialog;
   private View addStudentBottomSheetDialogView;

   // Variables for storing students data
   private String studentAID, studentName, studentEmail, studentPassword, studentGender = "Male";
   private RecyclerView studentRecyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students_accounts);


        // Set the layout for the activity
        setContentView(R.layout.activity_admin_students_accounts);

        // Initialize views
        initializeViews();

        // Display all students in the RecyclerView
        displayAllStudents();

        // Set button actions
        setAddStudentButtonAction(); // Add new student
        setBackButtonAction(); // Go back to previous activity
        setStudentGenderRadioGroupAction(); // Set gender of new student
        setSaveStudentButtonAction(); // Save new student data to database
        setCloseAddStudentDialogButtonAction(); // Close the "add student" dialog
    }


    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_student = findViewById(R.id.activity_admin_students_accounts_btn_add);
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);

        addStudentBottomSheetDialog = new BottomSheetDialog(AdminStudentsAccountsActivity.this, R.style.BottomSheetStyle);
        addStudentBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_student_account, null, false);

        btn_save_student = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_btn_save);
        btn_close_add_student_dialog = addStudentBottomSheetDialogView.findViewById(R.id.fragment_admin_new_student_btn_close);

        et_add_new_student_name = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_studet_name);
        et_add_new_student_aid = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_aid);
        et_add_new_student_password = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_password);
        et_add_new_student_email = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_email);

        rg_gender = addStudentBottomSheetDialogView.findViewById(R.id.fragment_new_student_rg_student_kind);

        studentRecyclerView = findViewById(R.id.activity_admin_students_accounts_recyclerview);
    }

    /** setAddStudentButtonAction()
     *  show Bottom Sheet Dialog
     **********************************************************************************************/
    public void setAddStudentButtonAction() {
        btn_add_student.setOnClickListener(v -> {

            addStudentBottomSheetDialog.setContentView(addStudentBottomSheetDialogView);
            addStudentBottomSheetDialog.show();
        });
    }

    /** setCloseAddStudentDialogButtonAction()
     *  close Bottom Sheet Dialog
     **********************************************************************************************/
    public void setCloseAddStudentDialogButtonAction() {
        btn_close_add_student_dialog.setOnClickListener(v -> {
            addStudentBottomSheetDialog.dismiss();
            clearAddStudentDialogEditTextFields();
        });
    }

    /** setSaveStudentButtonAction()
     *  check Validation Of Entered Data And save It
     **********************************************************************************************/
    public void setSaveStudentButtonAction() {
        btn_save_student.setOnClickListener(v -> {

            studentName = et_add_new_student_name.getText().toString();
            studentEmail = et_add_new_student_email.getText().toString();
            studentPassword = et_add_new_student_password.getText().toString();
            studentAID = et_add_new_student_aid.getText().toString().trim();


            if (TextUtils.isEmpty(studentName)) {
                et_add_new_student_name.setError("Is Required !");
                return;
            }
            if (TextUtils.isEmpty(studentAID)) {
                et_add_new_student_aid.setError("Is Required !");
                return;
            }

            if (TextUtils.isEmpty(studentEmail)) {
                et_add_new_student_email.setError("Is Required !");
                return;
            }
            if (!emailShouldEndsWithEdu(studentEmail)) {
                et_add_new_student_email.setError("Should End With .edu");
                return;
            }
            if (TextUtils.isEmpty(studentPassword)) {
                et_add_new_student_password.setError("Is Required !");
                return;
            }

            saveNewStudentToDatabase();
            clearAddStudentDialogEditTextFields();
            displayAllStudents();
        });
    }

    /** setStudentGenderRadioGroupAction()
     *  to get gender of student
     ******************************************************************************************/
    public void setStudentGenderRadioGroupAction(){

        rg_gender.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.fragment_new_student_rb_male)
                studentGender = "Male";
            else if (checkedId == R.id.fragment_new_student_rb_female)
                studentGender = "Female";


        });
    }

    /** setBackButtonAction()
     *  Back To The Previous Activity
     **********************************************************************************************/
    public void setBackButtonAction() {
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminHomeActivity.class)));
    }

    /** saveNewStudentToDatabase()
     * add New Student
     ******************************************************************************************/
    public void saveNewStudentToDatabase() {

        My_DB db = new My_DB(getBaseContext());

        studentAID = et_add_new_student_aid.getText().toString().trim();
        studentName = et_add_new_student_name.getText().toString().trim();
        studentEmail = et_add_new_student_email.getText().toString().trim();
        studentPassword = et_add_new_student_password.getText().toString().trim();


        Students student = new Students(studentAID, studentName, studentName, studentGender, studentEmail, studentPassword);
        boolean added = db.addNewStudent(student);

        if(added){
            Toast.makeText(this, studentName + "Is Successfully Saved ✔️", Toast.LENGTH_SHORT).show();
            addStudentBottomSheetDialog.dismiss();
        }
        displayAllStudents();
    }

    /** emailShouldEndsWithEdu()
     * Special Validation For Email To End With .edu
     **********************************************************************************************/
    public static boolean emailShouldEndsWithEdu(String input) {
        return !TextUtils.isEmpty(input) && input.endsWith(".edu");
    }

    /** clearAddStudentDialogEditTextFields()
     *  FillOut  EditTexts After Add New Student Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void clearAddStudentDialogEditTextFields() {

        et_add_new_student_name.setText("");
        et_add_new_student_email.setText("");
        et_add_new_student_password.setText("");
        et_add_new_student_aid.setText("");

    }

    /**displayAllStudents()
     **********************************************************************************************/
    public void displayAllStudents() {

        My_DB db = new My_DB(getBaseContext());

        ArrayList<Students> studentsArrayList = db.displayAllStudents();

        StudentRecyclerViewAdapter adapter = new StudentRecyclerViewAdapter(this, studentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        studentRecyclerView.setHasFixedSize(true);
        studentRecyclerView.setLayoutManager(lm);
        studentRecyclerView.setAdapter(adapter);
    }


    /**Search For Students
     **********************************************************************************************/
//    public void searchForStudents() {
//
//
//        My_DB db = new My_DB(getBaseContext());
//
//
//        ArrayList<Students> studentsArrayList = db.searchForStudents(tv_search_for_students.getText().toString());
//
//        studentRecyclerViewAdapter adapter = new studentRecyclerViewAdapter(this,studentsArrayList);
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
//
//        rv.setHasFixedSize(true);
//        rv.setLayoutManager(lm);
//        rv.setAdapter(adapter);
//    }

}