package com.studentzone.Admin_Classes.Admin_Models;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
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

public class departmentRecyclerViewAdapter extends RecyclerView.Adapter<departmentRecyclerViewAdapter.departmentViewHolder>implements Filterable
{

    private ArrayList<Departments> departmentsArrayList;
    private My_DB db;
    private BottomSheetDialog bottomSheetDialog;

    private EditText et_deptName_show, et_deptCode_show, et_deptName_edit, et_deptCode_edit;
    private Button btn_close_show_dept,btn_save_edit_dept,btn_close_edit_dept;


    private View bottomSheetDialogView;
    private AlertDialog.Builder builder;
    private  Departments department;

     ArrayList<Departments> filteredDepartmentNames;



    public departmentRecyclerViewAdapter(Context context, ArrayList<Departments> departmentsArrayList)
    {
        this.departmentsArrayList = departmentsArrayList;
        this.db = new My_DB(context);
        this.bottomSheetDialog = new BottomSheetDialog(context);
        this.builder = new AlertDialog.Builder(context);

        this.filteredDepartmentNames = departmentsArrayList;
    }

    /** onCreateViewHolder ()
     *  This method inflates the item layout for the doctor and returns a new instance of the doctorViewHolder class.
     **********************************************************************************************/
    @NonNull
    @NotNull
    @Override
    public departmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_department, null, false);

        departmentViewHolder departmentViewHolder = new departmentViewHolder(view);

        return departmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull departmentRecyclerViewAdapter.departmentViewHolder holder, int position) {

        department = departmentsArrayList.get(position);

        holder.bind(department);




        Departments departments = filteredDepartmentNames.get(position);


    }

    @Override
    public int getItemCount() {
        return departmentsArrayList.size();
    }

    public void addItem(Departments department)
    {
        departmentsArrayList.add(department);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();
                ArrayList<Departments> filteredList = new ArrayList<>();
                if (query.isEmpty()) {
                    filteredList = departmentsArrayList;
                } else {
                    for (Departments name : departmentsArrayList) {
                        if (name.getName().toLowerCase().contains(query)) {
                            filteredList.add(name);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDepartmentNames = (ArrayList<Departments>) filterResults.values;

                Log.d("TAG", "Filtered list size: " + filteredDepartmentNames.size());
                departmentsArrayList = filteredDepartmentNames;
                notifyDataSetChanged();

            }
        };
    }



    class departmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView tv_department_name, tv_department_code;
        private ImageButton ib_more;

        public departmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_department_name = itemView.findViewById(R.id.activity_admin_department_tv_name);
            tv_department_code = itemView.findViewById(R.id.activity_admin_department_tv_code);

            ib_more = itemView.findViewById(R.id.activity_admin_department_ib_more);

            ib_more.setOnClickListener(this);
        }

        public void bind(Departments department) {

            tv_department_name.setText(department.getName());
            tv_department_code.setText(department.getCode());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDepartmentDetailsDialog(department);
                }
            });
        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }

        /** showPopupMenu ()
         *  This method that shows a popup menu with the options to edit or delete a department ,
         *  when the user clicks on the more button in the department's card view.
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

            Departments department = departmentsArrayList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    showEditDepartmentDialog(department);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    showDeleteConfirmationDialog(department);
                    return true;
                default:
                    return false;
            }
        }

        private void showEditDepartmentDialog(Departments department) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_departments_edit, null, false);

            et_deptName_edit = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_et_department_name);
            et_deptCode_edit = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_et_department_code);

            btn_save_edit_dept = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_btn_save);
            btn_close_edit_dept = bottomSheetDialogView.findViewById(R.id.fragment_edit_department_btn_close);


            btn_save_edit_dept.setEnabled(false);

            et_deptName_edit.setText(department.getName());
            et_deptCode_edit.setText(department.getCode());


            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    btn_save_edit_dept.setEnabled(true);
                }
            };

            et_deptName_edit.addTextChangedListener(textWatcher);
            et_deptCode_edit.addTextChangedListener(textWatcher);


            btn_save_edit_dept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    department.setName(et_deptName_edit.getText().toString());
                    department.setCode(et_deptCode_edit.getText().toString());


                    boolean result = db.updateDepartment(department);

                    if(result == true){
                        departmentsArrayList.set(getAdapterPosition(), department);
                        notifyItemChanged(getAdapterPosition());

                        bottomSheetDialog.dismiss();
                    }


                }
            });

            btn_close_edit_dept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();

        }


        /** showDepartmentDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a Department when the user clicks on the Department's card view.
         *  It inflates a layout for displaying the details of a Department and sets the text of the views in the layout to the corresponding data of the Department.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void showDepartmentDetailsDialog(Departments department) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_departments_show, null, false);

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

        private void showDeleteConfirmationDialog(Departments department) {
            builder.setMessage("Are you sure you want to delete this Department?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            db.deleteDepartment(department.getCode());
                            departmentsArrayList.remove(getAdapterPosition());
                            Toast.makeText(bottomSheetDialog.getContext(), ""+department.getName()+" Department Successfully Deleted ✔️" , Toast.LENGTH_SHORT).show();
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
