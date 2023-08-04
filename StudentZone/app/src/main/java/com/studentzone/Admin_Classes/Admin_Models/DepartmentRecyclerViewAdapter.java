package com.studentzone.Admin_Classes.Admin_Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.Departments;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class DepartmentRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentRecyclerViewAdapter.departmentViewHolder>implements Filterable
{

    private ArrayList<Departments> departmentsList;
    private My_DB db;
    private BottomSheetDialog bottomSheetDialog;
    private EditText et_deptName_show, et_deptCode_show, et_deptName_edit, et_deptCode_edit;
    private Button btn_close_show_dept,btn_save_edit_dept,btn_close_edit_dept;
    private TextView tv_first_letter_of_dept;
    private View bottomSheetDialogView;
    private final AlertDialog.Builder builder;
    private  Departments department;
    private ArrayList<Departments> filteredDepartmentNames;
    private String code_before_update,name_before_update;


    public DepartmentRecyclerViewAdapter(Context context, ArrayList<Departments> departmentsList)
    {
        this.departmentsList = departmentsList;
        this.db = new My_DB(context);
        this.bottomSheetDialog = new BottomSheetDialog(context);
        this.builder = new AlertDialog.Builder(context);

        this.filteredDepartmentNames = departmentsList;
    }

    /** onCreateViewHolder ()
     *  This method inflates the item layout for the department and returns a new instance of the departmentViewHolder class.
     **********************************************************************************************/
    @NonNull
    @NotNull
    @Override
    public departmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_department, null, false);

        departmentViewHolder departmentViewHolder = new departmentViewHolder(view);

        return departmentViewHolder;
    }


    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the department's data to the view holder.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull @NotNull DepartmentRecyclerViewAdapter.departmentViewHolder holder, int position) {

        department = departmentsList.get(position);

        holder.setDepartmentData(department);


    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }

    public void addItem(Departments department)
    {

        departmentsList.add(department);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();
                ArrayList<Departments> filteredList = new ArrayList<>();
                if (query.isEmpty()) {
                    filteredList = departmentsList;
                } else {
                    for (Departments name : departmentsList) {
                        if (name.getName().toLowerCase().contains(query)) {
                            filteredList.add(name);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDepartmentNames = (ArrayList<Departments>) filterResults.values;

                Log.d("TAG", "Filtered list size: " + filteredDepartmentNames.size());
                departmentsList = filteredDepartmentNames;
                notifyDataSetChanged();

            }
        };
    }


    /** holder Class For departmentRecyclerViewAdapter
     *  It holds references to the views in the item layout and binds the data to the views.
     **********************************************************************************************/
    class departmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
         TextView tv_department_name, tv_department_code;
         ImageButton ib_more;

        public departmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_department_name = itemView.findViewById(R.id.activity_admin_department_tv_name);
            tv_department_code = itemView.findViewById(R.id.activity_admin_department_tv_code);
            tv_first_letter_of_dept = itemView.findViewById(R.id.activity_admin_department_tv_dn);

            ib_more = itemView.findViewById(R.id.activity_admin_department_ib_more);

            ib_more.setOnClickListener(this);
        }


        /** setDepartmentData()
         * Sets the data for a department by populating the appropriate views with the department's information.
         * Additionally, it assigns an onClickListener to the itemView to display detailed department information when clicked.
         **********************************************************************************************/
        public void setDepartmentData(Departments department) {

            String  abbreviation = "",courseName = department.getName();
            String[] words = courseName.split(" ");
            for (String word : words) {
                char firstLetter = word.charAt(0);
                abbreviation += firstLetter;
            }

            tv_department_name.setText(department.getName());
            tv_department_code.setText(department.getCode());
            tv_first_letter_of_dept.setText(abbreviation.toUpperCase(Locale.ROOT));



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayDepartmentDetailsDialog(department);
                }
            });
        }

        @Override
        public void onClick(View v) {
            displayPopupMenu(v);
        }

        /** displayPopupMenu ()
         *  This method that shows a popup menu with the options to edit or delete a department ,
         *  when the user clicks on the more button in the department's card view.
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

            Departments department = departmentsList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    displayEditDepartmentDialog(department);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    displayDeleteConfirmationDialog(department);
                    return true;
                default:
                    return false;
            }
        }

        /** displayDepartmentDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a Department when the user clicks on the Department's card view.
         *  It inflates a layout for displaying the details of a Department and sets the text of the views in the layout to the corresponding data of the Department.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void displayDepartmentDetailsDialog(Departments department) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_show_department, null, false);

            et_deptName_show = bottomSheetDialogView.findViewById(R.id.fragment_show_department_et_department_name);
            et_deptCode_show = bottomSheetDialogView.findViewById(R.id.fragment_show_department_et_department_code);
            btn_close_show_dept = bottomSheetDialogView.findViewById(R.id.fragment_show_department_btn_close);

            et_deptName_show.setText(department.getName());
            et_deptCode_show.setText(department.getCode());


            btn_close_show_dept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }



        /**displayEditDepartmentDialog ()
         * method shows a bottom sheet dialog for editing a doctor's information.
         * It inflates a layout for editing a Department's information and sets the text of the views in the layout to the corresponding data of the Department.
         * It also sets click listeners on the save and close buttons.
         * If the user clicks the save button, the data of the doctor is updated, and the updateDepartment method is called on the My_DB object to update the Department's information in the database.
         * The Department's data in the DepartmentsArrayList is also updated, and the RecyclerView is notified of the change.
         **********************************************************************************************/
        private void displayEditDepartmentDialog(Departments department) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_edit_department, null, false);

            et_deptName_edit = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_et_department_name);
            et_deptCode_edit = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_et_department_code);

            btn_save_edit_dept = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_btn_save);
            btn_close_edit_dept = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_btn_close);

            //This Lines To FillOut Text Fields With doctor Data
            et_deptName_edit.setText(department.getName());
            et_deptCode_edit.setText(department.getCode());

            // Keep track of the original department name and code
            String originalName = department.getName();
            String originalCode = department.getCode();

            //We Will Put it disabled until user edit any data of this doctor
            btn_save_edit_dept.setEnabled(false);


            /*
             *  textWatcher monitor changes in the name,and code fields of a form.
             *  Whenever any of these fields are modified, the afterTextChanged method of textWatcher is called,
             * which enables the btn_save button.
             * */
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    code_before_update = department.getCode();
                    name_before_update = department.getName();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Enable the save button if the text has changed from the original values
                    boolean dataChanged = !(et_deptName_edit.getText().toString().equals(originalName) && et_deptCode_edit.getText().toString().equals(originalCode));
                    btn_save_edit_dept.setEnabled( dataChanged);
                }
            };

            /*
             * The addTextChangedListener method is then called on each of the relevant EditText fields with textWatcher as the argument.
             * This sets up the TextWatcher instance to monitor changes to each field.
             **/
            et_deptName_edit.addTextChangedListener(textWatcher);
            et_deptCode_edit.addTextChangedListener(textWatcher);


            //This is the action of save the changed data or edited data  of doctor
            btn_save_edit_dept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //This lines to remember the user to enter data in department name and code
                    if (TextUtils.isEmpty(et_deptName_edit.getText().toString().trim())) {
                        et_deptName_edit.setError("Is Required !");
                        return;
                    }
                    if (TextUtils.isEmpty(et_deptCode_edit.getText().toString().trim())) {
                        et_deptCode_edit.setError("Is Required !");
                        return;
                    }

                    //This lines to send edited department to data base across pass new instance of department to db.updateDepartment
                    department.setName(et_deptName_edit.getText().toString());
                    department.setCode(et_deptCode_edit.getText().toString());


                    boolean result = db.updateDepartment(department,code_before_update,name_before_update);

                    if(result){
                        departmentsList.set(getAdapterPosition(), department);
                        notifyItemChanged(getAdapterPosition());

                        bottomSheetDialog.dismiss();
                    }
                }
            });

            //This is the action of close the bottomSheetDialogView of edit department
            btn_close_edit_dept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });


            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }



        /** displayDeleteConfirmationDialog()
         *  to show a confirmation dialog to the user before deleting a department.
         **********************************************************************************************/
        private void displayDeleteConfirmationDialog(Departments department) {
            builder.setMessage("Are you sure you want to delete \""+department.getName()+"\" Department? Note you will delete Courses Inside IT Too?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            db.deleteDepartment(department.getCode(),department.getId()+"");
                            departmentsList.remove(getAdapterPosition());
                            Toast.makeText(bottomSheetDialog.getContext(), ""+department.getName()+" Department Successfully Deleted" , Toast.LENGTH_SHORT).show();
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
