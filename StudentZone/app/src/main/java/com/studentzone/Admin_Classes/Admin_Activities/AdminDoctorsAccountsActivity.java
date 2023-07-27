package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.DoctorRecyclerViewAdapter;
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class AdminDoctorsAccountsActivity extends AppCompatActivity {
    private BottomSheetDialog addDoctorBottomSheetDialog;
    private View addDoctorBottomSheetDialogView;
    private Button btn_add_doctor, back_btn, btn_save_doctor, btn_close_add_doctor_dialog;
    private EditText et_add_new_doctor_name, et_add_new_doctor_password, et_add_new_doctor_email;
    private RadioGroup rg_gender;
    private RadioButton rb_male_doctor, rb_female_doctor;
    private String doctorName, doctorEmail, doctorPassword, doctorGender = "Male";
    private RecyclerView doctorRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctors_accounts);


        // Set the layout for the activity
        setContentView(R.layout.activity_admin_doctors_accounts);

        // Initialize views
        initializeViews();

        // Display all doctors in the RecyclerView
        displayAllDoctors();

        // Set button actions
        setAddDoctorButtonAction(); // Add new doctor
        setBackButtonAction(); // Go back to previous activity
        setDoctorGenderRadioGroupAction(); // Set gender of new doctor
        setSaveDoctorButtonAction(); // Save new doctor data to database
        setCloseAddDoctorDialogButtonAction(); // Close the "add doctor" dialog
    }

    /**inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_doctor = findViewById(R.id.activity_admin_doctors_accounts_btn_add);
        back_btn = findViewById(R.id.activity_admin_doctors_accounts_btn_back);

        addDoctorBottomSheetDialog = new BottomSheetDialog(AdminDoctorsAccountsActivity.this, R.style.BottomSheetStyle);
        addDoctorBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_doctor_account, null, false);

        btn_save_doctor = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_btn_save);
        btn_close_add_doctor_dialog = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_admin_new_doctor_btn_close);

        et_add_new_doctor_name = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_name);
        et_add_new_doctor_password = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_password);
        et_add_new_doctor_email = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_email);

        rg_gender = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rg_doctor_kind);
        rb_male_doctor = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rb_male);
        rb_female_doctor = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rb_female);

        doctorRecyclerView = findViewById(R.id.activity_admin_doctors_accounts_recyclerview);


    }


    /**show Bottom Sheet Dialog()
     **********************************************************************************************/
    public void setAddDoctorButtonAction() {
        btn_add_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDoctorBottomSheetDialog.setContentView(addDoctorBottomSheetDialogView);
                addDoctorBottomSheetDialog.show();
            }
        });
    }

    /** close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void setCloseAddDoctorDialogButtonAction() {
        btn_close_add_doctor_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDoctorBottomSheetDialog.dismiss();
                clearAddDoctorDialogEditTextFields();
                displayAllDoctors();
            }
        });
    }

    /** check Validation Of Entered Data And save It()
     **********************************************************************************************/
    public void setSaveDoctorButtonAction() {
        btn_save_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doctorName = et_add_new_doctor_name.getText().toString().trim();
                doctorEmail = et_add_new_doctor_email.getText().toString().trim();
                doctorPassword = et_add_new_doctor_password.getText().toString().trim();


                if (TextUtils.isEmpty(doctorName)) {
                    et_add_new_doctor_name.setError("Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(doctorEmail)) {
                    et_add_new_doctor_email.setError("Is Required !");
                    return;
                }
                if (!emailShouldEndsWithEdu(doctorEmail)) {
                    et_add_new_doctor_email.setError("Should End With .edu");
                    return;
                }
                if (TextUtils.isEmpty(doctorPassword)) {
                    et_add_new_doctor_password.setError("Is Required !");
                    return;
                }
                saveNewDoctorToDatabase();
                clearAddDoctorDialogEditTextFields();
                displayAllDoctors();
            }
        });
    }

    /** radioButtonGroupAction
     *  kindCheckedId, Get Index Of Checked User Kind
     ******************************************************************************************/
    public void setDoctorGenderRadioGroupAction(){

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.fragment_new_doctor_rb_male)
                    doctorGender = "Male";
                else if (checkedId == R.id.fragment_new_doctor_rb_female)
                    doctorGender = "Female";
            }
        });

    }
    /** Back To The Previous Activity()
     **********************************************************************************************/
    public void setBackButtonAction(){
        back_btn = findViewById(R.id.activity_admin_doctors_accounts_btn_back);
        back_btn.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }

    /** add New Doctor
     **********************************************************************************************/
    public void saveNewDoctorToDatabase() {


        My_DB db = new My_DB(getBaseContext());

        doctorName = et_add_new_doctor_name.getText().toString().trim();
        doctorEmail = et_add_new_doctor_email.getText().toString().trim();
        doctorPassword = et_add_new_doctor_password.getText().toString().trim();


        Doctors doctor = new Doctors(doctorName, doctorEmail, doctorPassword, doctorGender);
        boolean added = db.addNewDoctor(doctor);

        if(added){
            Toast.makeText(getBaseContext(), "Dr : "+doctor.getFName().toString()+" Is Successfully Saved ✔️", Toast.LENGTH_SHORT).show();
            addDoctorBottomSheetDialog.dismiss();
        }

        displayAllDoctors();
    }

    /** Special Validation For Email To End With .edu
     **********************************************************************************************/
    public static boolean emailShouldEndsWithEdu(String input) {
        return TextUtils.isEmpty(input) ? false : input.endsWith(".edu");
    }


    /**FillOut  EditTexts After Add New Doctor Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void clearAddDoctorDialogEditTextFields() {

        et_add_new_doctor_name.setText("");
        et_add_new_doctor_email.setText("");
        et_add_new_doctor_password.setText("");
    }

    /** Show All Doctors
     **********************************************************************************************/
    public void displayAllDoctors() {
        My_DB db = new My_DB(getBaseContext());

        ArrayList<Doctors> doctorsArrayList1 = db.showAllDoctors();

        DoctorRecyclerViewAdapter adapter = new DoctorRecyclerViewAdapter(this, doctorsArrayList1);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        doctorRecyclerView.setHasFixedSize(true);
        doctorRecyclerView.setLayoutManager(lm);
        doctorRecyclerView.setAdapter(adapter);
    }

}
