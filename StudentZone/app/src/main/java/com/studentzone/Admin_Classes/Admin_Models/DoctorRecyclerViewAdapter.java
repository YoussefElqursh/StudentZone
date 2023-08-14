package com.studentzone.Admin_Classes.Admin_Models;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class   DoctorRecyclerViewAdapter extends RecyclerView.Adapter<DoctorRecyclerViewAdapter.doctorViewHolder> {
    private final ArrayList<Doctors> doctorsArrayList;
    private final My_DB db;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;
    private EditText doctorName;
    private EditText doctorEmail;
    private EditText doctorPassword;
    private EditText doctorPhone;
    private Button btn_save, btn_close;
    private RadioButton rb_male , rb_female;
    private final AlertDialog.Builder builder;

    public DoctorRecyclerViewAdapter(Context context, ArrayList<Doctors> doctorsArrayList) {
        this.doctorsArrayList = doctorsArrayList;
        this.db = new My_DB(context);
        this.bottomSheetDialog = new BottomSheetDialog(context);
        this.builder = new AlertDialog.Builder(context);
    }

    /** onCreateViewHolder ()
     *  This method inflates the item layout for the doctor and returns a new instance of the doctorViewHolder class.
     **********************************************************************************************/
    @NonNull
    @Override
    public doctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_doctor,null,false);
        return new doctorViewHolder(view);
    }

    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the doctor's data to the view holder.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull doctorViewHolder holder, int position) {
        Doctors doctor = doctorsArrayList.get(position);

        holder.setDoctorData(doctor);

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return doctorsArrayList.size();
    }

    /** holder Class For doctorRecyclerViewAdapter
     *  It holds references to the views in the item layout and binds the data to the views.
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

        /** setDoctorData()
         * Sets the data for a doctor by populating the appropriate views with the doctor's information.
         * Additionally, it assigns an onClickListener to the itemView to display detailed doctor information when clicked.
         **********************************************************************************************/
        public void setDoctorData(Doctors doctor) {

            tv_doctor_name.setText(doctor.getFName());
            tv_doctor_password.setText(doctor.getPassword());

            if (doctor.getGender() != null && doctor.getGender().equals("Male")) {
                iv.setImageResource(R.drawable.ic_male_doctor);
            } else {
                iv.setImageResource(R.drawable.ic_female_doctor);
            }
            itemView.setOnClickListener(v -> displayDoctorDetailsDialog(doctor));
        }

        @Override
        public void onClick(View v) {
            displayPopupMenu(v);
        }

        /** displayPopupMenu ()
         *  This method that shows a popup menu with the options to edit or delete a doctor ,
         *  when the user clicks on the more button in the doctor's card view.
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
            Doctors doctor = doctorsArrayList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    displayEditDoctorDialog(doctor);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    displayDeleteConfirmationDialog(doctor);
                    return true;
                default:
                    return false;
            }
        }

        /** displayDoctorDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a doctor when the user clicks on the doctor's card view.
         *  It inflates a layout for displaying the details of a doctor and sets the text of the views in the layout to the corresponding data of the doctor.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void displayDoctorDetailsDialog(Doctors doctor) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_show_doctor_accout, null, false);

            doctorName = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_name);
            doctorEmail = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_email);
            doctorPassword = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_password);
            doctorPhone = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_phone);
            EditText doctorGender = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_gender);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_btn_close);

            doctorName.setText(doctor.getFName());
            doctorEmail.setText(doctor.getEmail());
            doctorPassword.setText(doctor.getPassword());
            doctorPhone.setText(doctor.getPhone());
            doctorGender.setText(doctor.getGender());

            btn_close.setOnClickListener(v -> bottomSheetDialog.dismiss());

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

        /**displayEditDoctorDialog ()
         * method shows a bottom sheet dialog for editing a doctor's information.
         * It inflates a layout for editing a doctor's information and sets the text of the views in the layout to the corresponding data of the doctor.
         * It also sets click listeners on the save and close buttons.
         * If the user clicks the save button, the data of the doctor is updated, and the updateDoctor method is called on the My_DB object to update the doctor's information in the database.
         * The doctor's data in the doctorsArrayList is also updated, and the RecyclerView is notified of the change.
         **********************************************************************************************/
        private void displayEditDoctorDialog(Doctors doctor) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_edit_doctor_accout, null, false);

            doctorName = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_name);
            doctorEmail = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_email);
            doctorPassword = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_password);
            doctorPhone = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_phone);
            btn_save = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_btn_save);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_btn_close);

            RadioGroup rg = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_rg_doctor_kind);
            rb_male = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_rb_male);
            rb_female = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_rb_female);


            //This Lines To FillOut Text Fields With doctor Data
            doctorName.setText(doctor.getFName());
            doctorEmail.setText(doctor.getEmail());
            doctorPhone.setText(doctor.getPhone());
            doctorPassword.setText(doctor.getPassword());

            //This Lines To Check Radio Button Which Detect The Gender Of Student
            if (doctor.getGender().equals("Male") && doctor.getGender() != null) {
                rb_male.setChecked(true);
            } else if (doctor.getGender().equals("Female") && doctor.getGender() != null) {
                rb_female.setChecked(true);
            }

            // Keep track of the original doctor name , pass
            String originalName = doctor.getFName();
            String originalPassword = doctor.getPassword();
            String originalPhone = doctor.getPhone();


            //We Will Put it disabled until user edit any data of this doctor
            btn_save.setEnabled(false);


            /*
             *  textWatcher monitor changes in the name, email,and password fields of a form.
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
                    if (rb_male.isChecked() && !doctor.getGender().equals("Male")) {
                        genderChanged = true;
                    } else if (rb_female.isChecked() && !doctor.getGender().equals("Female")) {
                        genderChanged = true;
                    }
                    boolean dataChanged = !(doctorName.getText().toString().equals(originalName) && doctorPassword.getText().toString().equals(originalPassword)&& doctorPhone.getText().toString().equals(originalPhone));
                    btn_save.setEnabled(genderChanged || dataChanged);

                }
            };

            /*
             * The addTextChangedListener method is then called on each of the relevant EditText fields with textWatcher as the argument.
             * This sets up the TextWatcher instance to monitor changes to each field.
             **/
            doctorName.addTextChangedListener(textWatcher);
            doctorPassword.addTextChangedListener(textWatcher);
            doctorPhone.addTextChangedListener(textWatcher);

            // Add listener to the radio group to enable the save button when the user changes the gender
            rg.setOnCheckedChangeListener((group, checkedId) -> {
                // Enable the save button if the text has changed from the original values || the gender is changed
                boolean genderChanged = false;
                if (rb_male.isChecked() && !doctor.getGender().equals("Male")) {
                    genderChanged = true;
                } else if (rb_female.isChecked() && !doctor.getGender().equals("Female")) {
                    genderChanged = true;
                }
                boolean dataChanged = !(doctorName.getText().toString().equals(originalName) && doctorPassword.getText().toString().equals(originalPassword)&& doctorPhone.getText().toString().equals(originalPhone));
                btn_save.setEnabled(genderChanged || dataChanged);
            });


            //This is the action of save the changed data or edited data  of doctor
            btn_save.setOnClickListener(v -> {


                //This lines to remember the user to enter data in student name and pass
                if (TextUtils.isEmpty(doctorName.getText().toString().trim())) {
                    doctorName.setError("Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(doctorPassword.getText().toString().trim())) {
                    doctorPassword.setError("Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(doctorPhone.getText().toString().trim()) || !doctorPhone.getText().toString().trim().startsWith("01") || doctorPhone.length()<11 || !android.util.Patterns.PHONE.matcher(doctorPhone.getText().toString().trim()).matches()) {
                    doctorPhone.setError("Please enter"+ "\n"+ "valid phone number!");
                    return;
                }

                //This lines to send edited doctor to data base across pass new instance of doctor to db.updateDoctor
                String gender = "";
                if (rb_male.isChecked()) {
                    gender = "Male";
                } else if (rb_female.isChecked()) {
                    gender = "Female";
                }

                doctor.setFName(doctorName.getText().toString());
                doctor.setEmail(doctorEmail.getText().toString());
                doctor.setPassword(doctorPassword.getText().toString());
                doctor.setPhone(doctorPhone.getText().toString());
                doctor.setGender(gender);


                //this lines to change the doctor icon if user changed it
                if (doctor.getGender() != null && doctor.getGender().equals("Male")) {
                    iv.setImageResource(R.drawable.ic_male_doctor);
                } else {
                    iv.setImageResource(R.drawable.ic_female_doctor);
                }

                db.updateDoctor(doctor);

                doctorsArrayList.set(getAdapterPosition(), doctor);
                notifyItemChanged(getAdapterPosition());

                bottomSheetDialog.dismiss();
                Toast.makeText(bottomSheetDialog.getContext(), "Changes saved." , Toast.LENGTH_SHORT).show();

            });

            //This is the action of close the bottomSheetDialogView of edit doctor
            btn_close.setOnClickListener(v -> bottomSheetDialog.dismiss());
            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }


        /** displayDeleteConfirmationDialog()
         *  to show a confirmation dialog to the user before deleting a student.
         **********************************************************************************************/
        private void displayDeleteConfirmationDialog(Doctors doctor) {
            builder.setMessage("Are you sure you want to delete  Dr: "+doctor.getFName()+"?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        db.deleteDoctor(doctor.getEmail());
                        doctorsArrayList.remove(getAdapterPosition());
                        Toast.makeText(bottomSheetDialog.getContext(), "Dr: "+doctor.getFName()+" Successfully Deleted." , Toast.LENGTH_SHORT).show();
                        notifyItemRemoved(getAdapterPosition());
                        dialog.dismiss();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
