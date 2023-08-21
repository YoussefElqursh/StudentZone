package com.studentzone.Admin_Classes.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Admin_Classes.Admin_Models.DoctorRecyclerViewAdapter;
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

/**
 * Activity for managing doctors in the admin panel.
 */
public class AdminDoctorsAccountsActivity extends AppCompatActivity {


    // Database object
    private final My_DB db = new My_DB(this);

    // Views
    private Button btn_add_doctor, back_btn, btn_save_doctor, btn_close_add_doctor_dialog, btn_show_search, btn_hide_search,btn_clear_searchKey;
    private EditText et_add_new_doctor_name, et_add_new_doctor_password, et_add_new_doctor_email,et_add_new_doctor_phone, et_search;
    private BottomSheetDialog addDoctorBottomSheetDialog;
    private View addDoctorBottomSheetDialogView;
    private RadioGroup rg_gender;

    // Variables for storing doctor data
    private String doctorName, doctorEmail, doctorPassword,doctorPhone, doctorGender = "Male";
    private RecyclerView doctorRecyclerView;
    private Toolbar toolbar;
    private LinearLayout ll_search,ll_no_search_results;
    private DoctorRecyclerViewAdapter adapter;
    private ArrayList<Doctors> filteredCoursesList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctors_accounts);

        // Initialize views
        initializeViews();

        // Display all doctors in the RecyclerView
        displayAllDoctors();

        // search for doctors
        setupSearchFunctionality();

        // Set button actions
        setAddDoctorButtonAction(); // Add new doctor
        setBackButtonAction(); // Go back to previous activity
        setDoctorGenderRadioGroupAction(); // Set gender of new doctor
        setSaveDoctorButtonAction(); // Save new doctor data to database
        setCloseAddDoctorDialogButtonAction(); // Close the "add doctor" dialog
        setButtonSearchAction(); // show search et
        setButtonBackSearchAction(); // hide search et
        clearSearchKey();//clear search edit text
    }

    /** initializeViews()
     *  inflate
     **********************************************************************************************/
    public void initializeViews() {
        btn_add_doctor = findViewById(R.id.activity_admin_doctors_accounts_btn_add);
        back_btn = findViewById(R.id.activity_admin_doctors_accounts_btn_back);
        btn_show_search = findViewById(R.id.activity_admin_doctors_accounts_btn_search);
        btn_hide_search = findViewById(R.id.activity_admin_doctors_accounts_btn_search_back);

        addDoctorBottomSheetDialog = new BottomSheetDialog(AdminDoctorsAccountsActivity.this, R.style.BottomSheetStyle);
        addDoctorBottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_add_doctor_account, null, false);

        btn_save_doctor = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_btn_save);
        btn_close_add_doctor_dialog = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_admin_new_doctor_btn_close);

        et_add_new_doctor_name = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_name);
        et_add_new_doctor_password = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_password);
        et_add_new_doctor_email = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_email);
        et_add_new_doctor_phone = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_et_doctor_phone);

        et_search = findViewById(R.id.activity_admin_doctors_accounts_et_search);
        btn_clear_searchKey = findViewById(R.id.activity_admin_doctors_accounts_btn_search_delete);

        rg_gender = addDoctorBottomSheetDialogView.findViewById(R.id.fragment_new_doctor_rg_doctor_kind);

        toolbar = findViewById(R.id.activity_admin_doctors_accounts_tbar);
        ll_search = findViewById(R.id.activity_admin_doctors_accounts_ll_search);
        ll_no_search_results = findViewById(R.id.activity_admin_doctors_accounts_ll_no_search_results);

        doctorRecyclerView = findViewById(R.id.activity_admin_doctors_accounts_recyclerview);


    }


    /** setAddDoctorButtonAction()
     *  show Bottom Sheet Dialog
     **********************************************************************************************/
    public void setAddDoctorButtonAction() {
        btn_add_doctor.setOnClickListener(v -> {

            addDoctorBottomSheetDialog.setContentView(addDoctorBottomSheetDialogView);
            addDoctorBottomSheetDialog.show();
        });
    }

    /** setCloseAddDoctorDialogButtonAction()
     *  close Bottom Sheet Dialog
     **********************************************************************************************/
    public void setCloseAddDoctorDialogButtonAction() {
        btn_close_add_doctor_dialog.setOnClickListener(v -> {
            addDoctorBottomSheetDialog.dismiss();
            clearAddDoctorDialogEditTextFields();
            displayAllDoctors();
        });
    }

    /** setSaveDoctorButtonAction()
     *  check Validation Of Entered Data And save It
     **********************************************************************************************/
    public void setSaveDoctorButtonAction() {
        btn_save_doctor.setOnClickListener(v -> {

            doctorName = et_add_new_doctor_name.getText().toString().trim();
            doctorEmail = et_add_new_doctor_email.getText().toString().trim();
            doctorPassword = et_add_new_doctor_password.getText().toString().trim();
            doctorPhone = et_add_new_doctor_phone.getText().toString().trim();


            if (TextUtils.isEmpty(doctorName)) {
                et_add_new_doctor_name.setError("Is Required !");
                return;
            }
            if (TextUtils.isEmpty(doctorPhone) || !doctorPhone.matches("01[0125]\\d{8}")) {
                et_add_new_doctor_phone.setError("Please enter"+ "\n"+ "valid phone number!");
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
        });
    }

    /** setDoctorGenderRadioGroupAction()
     *  to get gender of doctor
     ******************************************************************************************/
    public void setDoctorGenderRadioGroupAction(){
        rg_gender.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.fragment_new_doctor_rb_male)
                doctorGender = "Male";
            else if (checkedId == R.id.fragment_new_doctor_rb_female)
                doctorGender = "Female";
        });

    }
    /** setBackButtonAction()
     *  Back To The Previous Activity()
     **********************************************************************************************/
    public void setBackButtonAction(){
        back_btn = findViewById(R.id.activity_admin_doctors_accounts_btn_back);
        back_btn.setOnClickListener(v -> startActivity(new Intent(getBaseContext(),AdminHomeActivity.class)));
    }

    /** saveNewDoctorToDatabase ()
     *  add New Doctor
     **********************************************************************************************/
    public void saveNewDoctorToDatabase() {


        doctorName = et_add_new_doctor_name.getText().toString().trim();
        doctorEmail = et_add_new_doctor_email.getText().toString().trim();
        doctorPassword = et_add_new_doctor_password.getText().toString().trim();
        doctorPhone = et_add_new_doctor_phone.getText().toString().trim();


        Doctors doctor = new Doctors(doctorName, doctorEmail, doctorPassword, doctorGender,doctorPhone);
        boolean added = db.addNewDoctor(doctor);

        if(added){
            Toast.makeText(getBaseContext(), "Dr : "+doctor.getFName()+" Is Successfully Saved.", Toast.LENGTH_SHORT).show();
            addDoctorBottomSheetDialog.dismiss();
        }

        displayAllDoctors();
    }

    /** emailShouldEndsWithEdu()
     *  Special Validation For Email To End With .edu
     **********************************************************************************************/
    public static boolean emailShouldEndsWithEdu(String input) {
        return !TextUtils.isEmpty(input) && input.endsWith(".edu");
    }


    /** clearAddDoctorDialogEditTextFields()
     *  FillOut  EditTexts After Add New Doctor Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void clearAddDoctorDialogEditTextFields() {

        et_add_new_doctor_name.getText().clear();
        et_add_new_doctor_email.getText().clear();
        et_add_new_doctor_password.getText().clear();
        et_add_new_doctor_phone.getText().clear();
    }

    /** Display All Doctors()
     **********************************************************************************************/
    public void displayAllDoctors() {

        ArrayList<Doctors> doctorsList = db.displayDoctors("");
        filteredCoursesList = new ArrayList<>(doctorsList);

        // Adapter for the course RecyclerView
        adapter = new DoctorRecyclerViewAdapter(this, doctorsList); // assign list to adapter variable
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        doctorRecyclerView.setHasFixedSize(true);
        doctorRecyclerView.setLayoutManager(lm);
        doctorRecyclerView.setAdapter(adapter);
    }

    /**setButtonSearchAction()
     * Make button search show search edit text and hide toolbar
     **********************************************************************************************/
    public void setButtonSearchAction() {

        btn_show_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.INVISIBLE);
            ll_search.setVisibility(View.VISIBLE);

            //Show make a cursor focus on edit text when click on search button
            et_search.requestFocus(v.getTextDirection());

            //Show keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);

            //Make animation when click on search btn
            Animation animation = AnimationUtils.loadAnimation(AdminDoctorsAccountsActivity.this, R.anim.anim_activities_show_search);
            ll_search.startAnimation(animation);

        });
    }

    /**setButtonBackSearchAction()
     * Make button back Search hide search edit text and show toolbar
     **********************************************************************************************/
    public void setButtonBackSearchAction() {
        btn_hide_search.setOnClickListener(v -> {

            toolbar.setVisibility(View.VISIBLE);
            ll_search.setVisibility(View.INVISIBLE);

            et_search.getText().clear();

            //Make animation when click on search back
            Animation animation = AnimationUtils.loadAnimation(AdminDoctorsAccountsActivity.this, R.anim.anim_activities_hide_search);
            toolbar.startAnimation(animation);

            //Close Keyboard INPUT METHOD SERVICE when click on search button
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

        });
    }

    /**clearSearchKey()
     * this button appear in search edite text when user start in write and ,
     * clear the text in edite text when the user click on it
     **********************************************************************************************/
    private void clearSearchKey(){
        btn_clear_searchKey.setOnClickListener(v -> et_search.getText().clear());
    }

    /**setupSearchFunctionality()
     * Sets up the search functionality for the EditText view.
     * Adds a TextWatcher to monitor changes in the text as the user types.
     **********************************************************************************************/
    public void setupSearchFunctionality(){
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Convert the text to lowercase and remove leading/trailing whitespace
                String searchKey =  s.toString().toLowerCase();

                // Perform search operation as the user types in the edit text
                if(searchKey.isEmpty() || searchKey.trim().isEmpty())
                    displayAllDoctors();
                else
                    performSearch(searchKey.trim());


                if(searchKey.isEmpty())
                    btn_clear_searchKey.setVisibility(View.INVISIBLE);
                else
                    btn_clear_searchKey.setVisibility(View.VISIBLE);

                handleSearchQueryResult();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**performSearch()
     * This method get searched doctors from data base and pss it to RecyclerView to update it
     **********************************************************************************************/
    private  void performSearch(String searchKye){

        filteredCoursesList = db.displayDoctors(searchKye);
        adapter.updateDoctors(filteredCoursesList);

    }

    /**handleSearchQueryResult()
     * This method is likely called after performing a search
     * It ensures that the appropriate views are shown or hidden based on whether there are search results available
     **********************************************************************************************/
    public void handleSearchQueryResult(){
        if(filteredCoursesList.size()>0){
            doctorRecyclerView.setVisibility(View.VISIBLE);
            ll_no_search_results.setVisibility(View.INVISIBLE);
        }
        else {
            ll_no_search_results.setVisibility(View.VISIBLE);
            doctorRecyclerView.setVisibility(View.INVISIBLE);
        }
    }
}