package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.doctorRecyclerViewAdapter;
import com.studentzone.Admin_Classes.Admin_Models.studentRecyclerViewAdapter;
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
import com.studentzone.R;

import java.util.ArrayList;

public class AdminDoctorsAccountsActivity extends AppCompatActivity {
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;
    private Button btn_add, btn_back, btm_sheet_dia_btn_save, btm_sheet_dia_btn_close;
    private EditText btm_sheet_dia_add_new_et_doctor_name, btm_sheet_dia_add_new_et_doctor_password, btm_sheet_dia_add_new_et_doctor_email;
    private RadioGroup btm_sheet_dia_rg;
    private RadioButton btm_sheet_dia_rb_male, btm_sheet_dia_rb_female;
    private String name, email, password, gender = "Male";
    private RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctors_accounts);

        inflate();
        showAllDoctors();

        buttonAddAction();
        radioButtonGroupAction();
        saveDoctorData();
        closeBottomSheet();
        buttonBackAction();

    }

    /**inflate
     **********************************************************************************************/
    public void inflate() {
        btn_add = findViewById(R.id.activity_admin_doctors_accounts_btn_add);
        btn_back = findViewById(R.id.activity_admin_doctors_accounts_btn_back);

        bottomSheetDialog = new BottomSheetDialog(AdminDoctorsAccountsActivity.this, R.style.BottomSheetStyle);
        bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_doctor_account, null, false);

        btm_sheet_dia_btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_btn_save);
        btm_sheet_dia_btn_close = bottomSheetDialogView.findViewById(R.id.fragment_admin_new_doctor_btn_close);

        btm_sheet_dia_add_new_et_doctor_name = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_name);
        btm_sheet_dia_add_new_et_doctor_password = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_password);
        btm_sheet_dia_add_new_et_doctor_email = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_email);

        btm_sheet_dia_rg = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rg_doctor_kind);
        btm_sheet_dia_rb_male = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rb_male);
        btm_sheet_dia_rb_female = bottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rb_female);

        rv = findViewById(R.id.activity_admin_doctors_accounts_recyclerview);


    }


    /**show Bottom Sheet Dialog()
     **********************************************************************************************/
    public void buttonAddAction() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }

    /** close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void closeBottomSheet() {
        btm_sheet_dia_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                nullEditTexts();
                showAllDoctors();
            }
        });
    }

    /**check Validation Of Entered Data And save It()
     **********************************************************************************************/
    public void saveDoctorData() {
        btm_sheet_dia_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = btm_sheet_dia_add_new_et_doctor_name.getText().toString().trim();
                email = btm_sheet_dia_add_new_et_doctor_email.getText().toString().trim();
                password = btm_sheet_dia_add_new_et_doctor_password.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    btm_sheet_dia_add_new_et_doctor_name.setError("Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    btm_sheet_dia_add_new_et_doctor_email.setError("Is Required !");
                    return;
                }
                if (!emailShouldEndsWithEdu(email)) {
                    btm_sheet_dia_add_new_et_doctor_email.setError("Should End With .edu");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    btm_sheet_dia_add_new_et_doctor_password.setError("Is Required !");
                    return;
                }
                addNewDoctor();
                nullEditTexts();
                showAllDoctors();
            }
        });
    }

    /**radioButtonGroupAction
     *kindCheckedId, Get Index Of Checked User Kind in
     ******************************************************************************************/
    public void radioButtonGroupAction(){

        btm_sheet_dia_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.fragment_new_doctor_rb_male)
                    gender = "Male";
                else if (checkedId == R.id.fragment_new_doctor_rb_female)
                    gender = "Female";
            }
        });

    }
    /**Back To The Previous Activity()
     **********************************************************************************************/
    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_admin_doctors_accounts_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }

    /**add New Doctor
     **********************************************************************************************/
    public void addNewDoctor() {


        My_DB db = new My_DB(getBaseContext());

        name = btm_sheet_dia_add_new_et_doctor_name.getText().toString().trim();
        email = btm_sheet_dia_add_new_et_doctor_email.getText().toString().trim();
        password = btm_sheet_dia_add_new_et_doctor_password.getText().toString().trim();

        Doctors doctor = new Doctors(name,email,password,gender);
        boolean added = db.addNewDoctor(doctor);

        if(added){
            Toast.makeText(getBaseContext(), "Dr : "+doctor.getFName().toString()+" Is Successfully Saved ✔️", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        }

        showAllDoctors();
    }

    /**
     * Special Validation For Email To End With .edu
     **********************************************************************************************/
    public static boolean emailShouldEndsWithEdu(String input) {
        return TextUtils.isEmpty(input) ? false : input.endsWith(".edu");
    }


    /**FillOut  EditTexts After Add New Doctor Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void nullEditTexts() {

        btm_sheet_dia_add_new_et_doctor_name.setText("");
        btm_sheet_dia_add_new_et_doctor_email.setText("");
        btm_sheet_dia_add_new_et_doctor_password.setText("");
    }

    /**Show All Doctors
     **********************************************************************************************/
    public void showAllDoctors() {
        My_DB db = new My_DB(getBaseContext());

        ArrayList<Doctors> doctorsArrayList1 = db.showAllDoctors();

        doctorRecyclerViewAdapter adapter = new doctorRecyclerViewAdapter(this, doctorsArrayList1);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }

}
