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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.Courses;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Adapter class for the RecyclerView displaying courses.
 */
public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.subjectViewHolder>
{

    private final ArrayList<Courses> coursesList;
    private final My_DB db;
    private BottomSheetDialog bottomSheetDialog;
    private Button btn_save_edit_course; 
    private View bottomSheetDialogView;
    private final AlertDialog.Builder builder;

    /**
     * Constructor for the CourseRecyclerViewAdapter.
     *
     * @param context     The context of the adapter.
     * @param coursesList The list of courses to display.
     **********************************************************************************************/
    public CourseRecyclerViewAdapter(Context context, ArrayList<Courses> coursesList)
    {
        this.coursesList = coursesList;
        this.db = new My_DB(context);
        this.bottomSheetDialog = new BottomSheetDialog(context);
        this.builder = new AlertDialog.Builder(context);
    }

    /** onCreateViewHolder ()
     *  This method inflates the item layout for the courses and returns a new instance of the courseViewHolder class.
     *  Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *   @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     *   @param viewType The view type of the new View.
     *   @return A new ViewHolder that holds a View of the given view type.
     **********************************************************************************************/
    @NonNull
    @NotNull
    @Override
    public subjectViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_model_subject,null,false);

        subjectViewHolder subjectViewHolder = new subjectViewHolder(view);

        return subjectViewHolder;
    }

    /** onBindViewHolder ()
     *  IT's CallBack Method
     *  This method is called for each item in the list to bind the data to the view.
     *  It gets the doctor at the current position in the list and binds the course's data to the view holder.
     *  @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position in the data set.
     *  @param position The position of the item within the adapter's data set.
     **********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull @NotNull CourseRecyclerViewAdapter.subjectViewHolder holder, int position)
    {
        Courses course = coursesList.get(position);

        holder.bindCourseData(course);

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.itemView.startAnimation(animation);
    }

    /**getItemCount()
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     **********************************************************************************************/
    @Override
    public int getItemCount()
    {
        return coursesList.size();
    }


    /** holder Class For courseRecyclerViewAdapter
     *  It holds references to the views in the item layout and binds the data to the views.
     **********************************************************************************************/
    class subjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView tv_course_name, tv_course_code, tv_first_letter_of_course;
        ImageButton ib_more;

        public subjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_course_name = itemView.findViewById(R.id.activity_admin_subject_tv_sub_name);
            tv_course_code  = itemView.findViewById(R.id.activity_admin_subject_tv_sub_code);
            tv_first_letter_of_course  = itemView.findViewById(R.id.activity_admin_subject_tv_sn);
            ib_more = itemView.findViewById(R.id.activity_admin_subject_ibtn_more);

            ib_more.setOnClickListener(this);
        }

        /** setCourseData()
         * Binds the course data to the views in the ViewHolder.
         * Additionally, it assigns an onClickListener to the itemView to display detailed department information when clicked.
         *  @param course The course object to bind.
         **********************************************************************************************/
        public void bindCourseData(Courses course) {

            String abbreviation = "";
            String courseName = course.getName();
            String[] words = courseName.split(" ");
            for (String word : words) {
                char firstLetter = word.charAt(0);
                abbreviation += firstLetter;
            }

            tv_course_name.setText(course.getName());
            tv_course_code.setText(course.getCode());
            tv_first_letter_of_course.setText(abbreviation.toUpperCase(Locale.ROOT));

            itemView.setOnClickListener(v -> displayCourseDetailsDialog(course));
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

            Courses course = coursesList.get(getAdapterPosition());

            switch (item.getItemId()) {
                case R.id.models_ibtn_menu_edit:
                    displayEditCourseDialog(course);
                    return true;
                case R.id.models_ibtn_menu_delete:
                    displayDeleteConfirmationDialog(course);
                    return true;
                default:
                    return false;
            }
        }

        /** displayCourseDetailsDialog ()
         *  This method shows a bottom sheet dialog with the details of a Department when the user clicks on the Department's card view.
         *  It inflates a layout for displaying the details of a Department and sets the text of the views in the layout to the corresponding data of the Department.
         *  It also sets a click listener on the close button to dismiss the dialog.
         **********************************************************************************************/
        private void displayCourseDetailsDialog(Courses course) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_show_subject, null, false);

            EditText et_courseName_show = bottomSheetDialogView.findViewById(R.id.fragment_show_subject_et_name);
            EditText et_courseCode_show = bottomSheetDialogView.findViewById(R.id.fragment_show_subject_et_code);
            EditText et_courseDepartment_show = bottomSheetDialogView.findViewById(R.id.fragment_show_subject_et_department);
            EditText et_courseDoctor_show = bottomSheetDialogView.findViewById(R.id.fragment_show_subject_et_doctor);
            EditText et_coursePreRequest_show = bottomSheetDialogView.findViewById(R.id.fragment_show_subject_et_previous);
            Button btn_close_show_course = bottomSheetDialogView.findViewById(R.id.fragment_show_subject_btn_close);

            // Set the text of the views to the corresponding data of the course
            et_courseName_show.setText(course.getName());
            et_courseCode_show.setText(course.getCode());
            et_courseDepartment_show.setText(db.getDepartmentNameById(course.getDepartment()));
            et_courseDoctor_show.setText(db.getDoctorNameById(course.getDoctor()));
            et_coursePreRequest_show.setText(db.getPreRequestNameById(course.getPreRequest()));

            // Set a click listener on the close button to dismiss the dialog
            btn_close_show_course.setOnClickListener(v -> bottomSheetDialog.dismiss());

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }

        /**displayEditCourseDialog ()
         * method shows a bottom sheet dialog for editing a doctor's information.
         * It inflates a layout for editing a Department's information and sets the text of the views in the layout to the corresponding data of the Department.
         * It also sets click listeners on the save and close buttons.
         * If the user clicks the save button, the data of the doctor is updated, and the updateDepartment method is called on the My_DB object to update the Department's information in the database.
         * The Department's data in the DepartmentsArrayList is also updated, and the RecyclerView is notified of the change.
         **********************************************************************************************/
        private void displayEditCourseDialog(Courses course) {
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_admin_subject_edit, null, false);

            EditText et_courseName_edit = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_et_name);
            EditText et_courseCode_edit = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_et_code);

            btn_save_edit_course = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_btn_save);
            Button btn_close_edit_course = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_btn_close);

            SearchableSpinner departmentSpinner = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_sp_department);
            SearchableSpinner doctorSpinner = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_sp_doctor_name);
            SearchableSpinner preRequestSpinner = bottomSheetDialogView.findViewById(R.id.fragment_edit_subject_sp_subject_pre_request);

            /**Fills out the three spinners with data from the database.
             **********************************************************************************************/
            List<String> deptNames = db.getAllDepartmentsNames();
            List<String> docNames = db.getAllDoctorsNames();
            List<String> coursesName = db.getAllCoursesNames();

            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, deptNames);
            arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(arrayAdapter1);

            docNames.add(0,"None");
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, docNames);
            arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doctorSpinner.setAdapter(arrayAdapter2);

            coursesName.add(0,"None");
            coursesName.remove(course.getName());

            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, coursesName);
            arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            preRequestSpinner.setAdapter(arrayAdapter3);

            // Keep track of the original department name and code
            String originalName = course.getName();
            String originalCode = course.getCode();
            String originalDept = db.getDepartmentNameById(course.getDepartment());
            String originalDoctor= db.getDoctorNameById(course.getDoctor());
            String originalPreRequest = db.getPreRequestNameById(course.getPreRequest());



            //This Lines To FillOut Text Fields With Course Data
            et_courseName_edit.setText(originalName);
            et_courseCode_edit.setText(originalCode);
            selectItemFromSpinner(originalDept, departmentSpinner);
            selectItemFromSpinner(originalDoctor, doctorSpinner);
            selectItemFromSpinner(originalPreRequest, preRequestSpinner);


            //We Will Put it disabled until user edit any data of this doctor
            btn_save_edit_course.setEnabled(false);


            /*
            This is an anonymous inner class implementing the TextWatcher interface
             *  textWatcher monitor changes in the name,and code fields of a form.
             *  Whenever any of these fields are modified, the afterTextChanged method of textWatcher is called,
             *  which enables the btn_save button.
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
                    // Enable the save button if the text or spinners has changed from the original values
                    boolean dataChanged = (!departmentSpinner.getSelectedItem().equals(originalDept) || !et_courseName_edit.getText().toString().equals(originalName) || !et_courseCode_edit.getText().toString().equals(originalCode) || !doctorSpinner.getSelectedItem().toString().equals(originalDoctor) || !preRequestSpinner.getSelectedItem().toString().equals(originalPreRequest));

                    btn_save_edit_course.setEnabled( dataChanged);
                }
            };

            /*
             * The addTextChangedListener method is then called on each of the relevant EditText fields with textWatcher as the argument.
             * This sets up the TextWatcher instance to monitor changes to each field.
             **/
            et_courseName_edit.addTextChangedListener(textWatcher);
            et_courseCode_edit.addTextChangedListener(textWatcher);


            //is added to detect changes in the selected department.
            departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    boolean dataChanged = (!departmentSpinner.getSelectedItem().equals(originalDept) || !et_courseName_edit.getText().toString().equals(originalName) || !et_courseCode_edit.getText().toString().equals(originalCode) || !doctorSpinner.getSelectedItem().toString().equals(originalDoctor) || !preRequestSpinner.getSelectedItem().toString().equals(originalPreRequest));
                    btn_save_edit_course.setEnabled( dataChanged);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            //is added to detect changes in the selected doctor.
            doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    boolean dataChanged = (!departmentSpinner.getSelectedItem().equals(originalDept) || !et_courseName_edit.getText().toString().equals(originalName) || !et_courseCode_edit.getText().toString().equals(originalCode) || !doctorSpinner.getSelectedItem().toString().equals(originalDoctor) || !preRequestSpinner.getSelectedItem().toString().equals(originalPreRequest));
                    btn_save_edit_course.setEnabled( dataChanged);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            //is added to detect changes in the selected preRequest.
            preRequestSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    boolean dataChanged = (!departmentSpinner.getSelectedItem().equals(originalDept) || !et_courseName_edit.getText().toString().equals(originalName) || !et_courseCode_edit.getText().toString().equals(originalCode) || !doctorSpinner.getSelectedItem().toString().equals(originalDoctor) || !preRequestSpinner.getSelectedItem().toString().equals(originalPreRequest));
                    btn_save_edit_course.setEnabled( dataChanged);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            //validates the entered data, updates the course information, and notifies the RecyclerView of the change.
            btn_save_edit_course.setOnClickListener(v -> {

                //This lines to remember the user to enter data in department name and code
                if (TextUtils.isEmpty(et_courseName_edit.getText().toString().trim())) {
                    et_courseName_edit.setError("This Filed Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(et_courseCode_edit.getText().toString().trim())) {
                    et_courseCode_edit.setError("This Filed Is Required !");
                    return;
                }

                //This lines to send edited course to data base across pass new instance of course to db.updateCourse
                course.setName(et_courseName_edit.getText().toString());
                course.setCode(et_courseCode_edit.getText().toString());
                course.setDepartment(db.getDepartmentIdByName(departmentSpinner.getSelectedItem().toString()));
                course.setDoctor(db.getDoctorIdByName(doctorSpinner.getSelectedItem().toString()));
                course.setPreRequest(db.getPreRequestIdByName(preRequestSpinner.getSelectedItem().toString()));

                boolean result = db.updateCourse(course,originalCode,originalName);

                if(result){
                    coursesList.set(getAdapterPosition(), course);
                    notifyItemChanged(getAdapterPosition());

                    bottomSheetDialog.dismiss();
                }

            });

            //added to handle the close button click. It dismisses the dialog without saving any changes.
            btn_close_edit_course.setOnClickListener(v -> bottomSheetDialog.dismiss());

            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
        }



        /** displayDeleteConfirmationDialog()
         *  to show a confirmation dialog to the user before deleting a department.
         **********************************************************************************************/
        private void displayDeleteConfirmationDialog(Courses course) {
            builder.setMessage("Are you sure you want to delete \""+course.getName()+"\" Course?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {

                        db.deleteCourse(course.getCode(),course.getId()+"");
                        coursesList.remove(getAdapterPosition());
                        Toast.makeText(bottomSheetDialog.getContext(), ""+course.getName()+" Course Successfully Deleted" , Toast.LENGTH_SHORT).show();

                        // Add the new course to the spinner adapter
                        notifyItemRemoved(getAdapterPosition());
                        dialog.dismiss();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());
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
