package com.studentzone.Admin_Classes.Admin_Models;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
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
import com.studentzone.Data_Base.Doctors;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;

public class doctorRecyclerViewAdapter extends RecyclerView.Adapter<doctorRecyclerViewAdapter.doctorViewHolder> {
    private ArrayList<Doctors> doctorsArrayList;
    private My_DB db;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;
    private EditText name, email, password, gender;
    private Button btn_save, btn_close;
    private RadioGroup rg;
    private RadioButton rb_male , rb_female;
    private AlertDialog.Builder builder;
    Doctors doctor;

    public doctorRecyclerViewAdapter(Context context,ArrayList<Doctors> doctorsArrayList) {
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
        doctorViewHolder doctorViewHolder = new doctorViewHolder(view);
        return doctorViewHolder;
    }

    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the doctor's data to the view holder.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull doctorViewHolder holder, int position) {
        doctor = doctorsArrayList.get(position);
        holder.bind(doctor);
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

        public void bind(Doctors doctor) {

            tv_doctor_name.setText(doctor.getFName());
            tv_doctor_password.setText(doctor.getPassword());

            if (doctor.getGender() != null && doctor.getGender().equals("Male")) {
                iv.setImageResource(R.drawable.ic_male_doctor);
            } else {
                iv.setImageResource(R.drawable.ic_female_doctor);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDoctorDetailsDialog(doctor);
                }
            });
        }

        @Override
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
            Doctors doctor = doctorsArrayList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    showEditDoctorDialog(doctor);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    showDeleteConfirmationDialog(doctor);
                    return true;
                default:
                    return false;
            }
        }

        /** showDoctorDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a doctor when the user clicks on the doctor's card view.
         *  It inflates a layout for displaying the details of a doctor and sets the text of the views in the layout to the corresponding data of the doctor.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void showDoctorDetailsDialog(Doctors doctor) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_doctors_accouts_show, null, false);

            name = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_name);
            email = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_email);
            password = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_password);
            gender = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_et_doctor_gender);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_show_doctor_btn_close);

            name.setText(doctor.getFName());
            email.setText(doctor.getEmail());
            password.setText(doctor.getPassword());
            gender.setText(doctor.getGender());

            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

        /**showEditDoctorDialog ()
         * method shows a bottom sheet dialog for editing a doctor's information.
         * It inflates a layout for editing a doctor's information and sets the text of the views in the layout to the corresponding data of the doctor.
         * It also sets click listeners on the save and close buttons.
         * If the user clicks the save button, the data of the doctor is updated, and the updateDoctor method is called on the My_DB object to update the doctor's information in the database.
         * The doctor's data in the doctorsArrayList is also updated, and the RecyclerView is notified of the change.
         **********************************************************************************************/
        private void showEditDoctorDialog(Doctors doctor) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_doctors_accouts_edit, null, false);

            name = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_name);
            email = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_email);
            password = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_et_doctor_password);
            btn_save = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_btn_save);
            btn_close = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_btn_close);

            rg = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_rg_doctor_kind);
            rb_male = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_rb_male);
            rb_female = bottomSheetDialogView.findViewById(R.id.fragment_edit_doctor_rb_female);

            btn_save.setEnabled(false);

            name.setText(doctor.getFName());
            email.setText(doctor.getEmail());
            password.setText(doctor.getPassword());


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
                    btn_save.setEnabled(true);
                }
            };
              /*
               * The addTextChangedListener method is then called on each of the relevant EditText fields with textWatcher as the argument.
               * This sets up the TextWatcher instance to monitor changes to each field.
               **/
               name.addTextChangedListener(textWatcher);
               email.addTextChangedListener(textWatcher);
               password.addTextChangedListener(textWatcher);


            // Add listener to the radio group to enable the save button when the user changes the gender
            rg.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == rb_male.getId()) {
                    if (!doctor.getGender().equals("Male")) {
                        btn_save.setEnabled(true);
                    }
                } else if (checkedId == rb_female.getId()) {
                    if (!doctor.getGender().equals("Female")) {
                        btn_save.setEnabled(true);
                    }
                }
            });





            if (doctor.getGender().equals("Male") && doctor.getGender() != null) {
                rb_male.setChecked(true);
            } else if (doctor.getGender().equals("Female") && doctor.getGender() != null) {
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

                    doctor.setFName(name.getText().toString());
                    doctor.setEmail(email.getText().toString());
                    doctor.setPassword(password.getText().toString());
                    doctor.setGender(gender);

                    if (doctor.getGender() != null && doctor.getGender().equals("Male")) {
                        iv.setImageResource(R.drawable.ic_male_doctor);
                    } else {
                        iv.setImageResource(R.drawable.ic_female_doctor);
                    }

                    db.updateDoctor(doctor);



                    doctorsArrayList.set(getAdapterPosition(), doctor);
                    notifyItemChanged(getAdapterPosition());

                    bottomSheetDialog.dismiss();
                    Toast.makeText(bottomSheetDialog.getContext(), "Changes saved ✔️" , Toast.LENGTH_SHORT).show();

                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

        private void showDeleteConfirmationDialog(Doctors doctor) {
            builder.setMessage("Are you sure you want to delete this doctor's information?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            db.deleteDoctor(doctor.getEmail());
                            doctorsArrayList.remove(getAdapterPosition());
                            Toast.makeText(bottomSheetDialog.getContext(), "Dr: "+doctor.getFName()+" Successfully Deleted ✔️" , Toast.LENGTH_SHORT).show();
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
