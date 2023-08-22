package com.studentzone.Student_Classes.Student_Models.SubjectModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;

import java.util.ArrayList;
import java.util.Locale;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{

    Context context ;
    ArrayList<SubjectModel> arrayList2 =new ArrayList<>();
    My_DB Db;

    private BottomSheetDialog bottomSheetDialog;

    private View bottomSheetDialogView;
    private final AlertDialog.Builder builder;






    public SubjectAdapter(Context context, ArrayList<SubjectModel> arrayList2) {
        this.context=context;
        Db = new My_DB(context);

        this.arrayList2 = arrayList2;
        this.bottomSheetDialog = new BottomSheetDialog(context);
        this.builder = new AlertDialog.Builder(context);
        filterPassedSubjects();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_student_model_subject,parent,false);//holder have model
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1_n.setText(arrayList2.get(position).getSubjectName());
        holder.tv2_c.setText(arrayList2.get(position).getCodeName());
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
        SubjectModel model = arrayList2.get(position);
        String  abbreviation = "";
        String courseName = model.getSubjectName();
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }
        holder.tv_3.setText(abbreviation.toUpperCase(Locale.ROOT));


    }

    private void filterPassedSubjects() {
        ArrayList<SubjectModel> passedSubjects = new ArrayList<>();
        for (SubjectModel subject : arrayList2) {
            String subjectName = subject.getSubjectName();
            int id = Db.get_Id_course_by_CourseName(subjectName);
            int degree = Db.getSubjectDegree_Id(id);

            if (degree >= 50) {
                passedSubjects.add(subject);
            }
        }
        arrayList2.removeAll(passedSubjects);
    }



    @Override
    public int getItemCount() {
        return arrayList2.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {//holder
        ImageButton IM;
        TextView tv1_n,tv2_c,tv_3;
        CardView crv;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1_n=itemView.findViewById(R.id.activity_student_subject_tv_sub_name);//store inflate in object
            tv2_c=itemView.findViewById(R.id.activity_student_subject_tv_sub_code);
            crv=itemView.findViewById(R.id.activity_student_subject_iv_cv);
            tv_3=itemView.findViewById(R.id.activity_student_subject_tv_sn);
            IM=itemView.findViewById(R.id.activity_student_subject_ibtn_more);
            IM.setOnClickListener(this);

        }
        private void displayPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.activity_student_subjects_model_more_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();

        }

        @Override
        public void onClick(View view) {
            displayPopupMenu(view);
        }


        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            SubjectModel course = arrayList2.get(getAdapterPosition());

            switch (menuItem.getItemId()) {
                case R.id.activity_student_subjects_model_more_menu_i_info:
                    showDetailsBottomSheet(course.SubjectName,course.CodeName);
                    return true;
                case R.id.activity_student_subjects_model_more_menu_i_disenroll:
                    displayDeleteConfirmation_Dialog(course.SubjectName);


                    return true;
                default:
                    return false;
            }

        }

        private void showDetailsBottomSheet(String courseName,String courseCode){
            bottomSheetDialog = new BottomSheetDialog(itemView.getContext(), R.style.BottomSheetStyle);
            bottomSheetDialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.fragment_student_info_regestration_subject, null, false);
            bottomSheetDialog.setContentView(bottomSheetDialogView);
            bottomSheetDialog.show();
            EditText et_courseName_show = bottomSheetDialogView.findViewById(R.id.fragment_student_regestration_subjects_info_et_name);
            EditText et_courseCode_show = bottomSheetDialogView.findViewById(R.id.fragment_student_regestration_subjects_info_et_code);
            EditText et_courseDepartment_show = bottomSheetDialogView.findViewById(R.id.fragment_student_regestration_subjects_info_et_department);
            EditText et_courseDoctor_show = bottomSheetDialogView.findViewById(R.id.fragment_student_regestration_subjects_info_et_doctor_name);
            EditText et_coursePreRequest_show = bottomSheetDialogView.findViewById(R.id.fragment_student_regestration_subjects_info_et_previous);
            Button btn_close_show_course = bottomSheetDialogView.findViewById(R.id.fragment_student_regestration_subjects_info_btn_close);

            et_courseName_show.setText(courseName);
            et_courseCode_show.setText(courseCode);
          String  Dep_Id= String.valueOf(Db.getDepartmentIdByCourseName(courseName));
         String Dep_Name= Db.getDepartmentName_ById(Integer.parseInt(Dep_Id));
            et_courseDepartment_show.setText(Dep_Name);
            int Doc_id=Db.getDoctorIdByCourseName(courseName);
            et_courseDoctor_show.setText(Db.getDoctorNameById(Doc_id));

          int id=  Db.getPreRequestIdBy_Name(courseName);
            et_coursePreRequest_show.setText(Db.getPreRequestNameId(id));
            btn_close_show_course.setOnClickListener(v -> bottomSheetDialog.dismiss());

        }
        private void displayDeleteConfirmation_Dialog(String course) {
            builder.setMessage("Are you sure you want to dis enroll \""+course+"\" Course?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        String subjectName= course;
                        int course_id=Db.get_Id_course_by_CourseName(subjectName);
                        Db.deleteSubjectfromEnrollment(course_id);
                        arrayList2.remove(getAdapterPosition());
                        Toast.makeText(bottomSheetDialog.getContext(), ""+course+" Course Successfully Deleted" , Toast.LENGTH_SHORT).show();

                        // Add the new course to the spinner adapter
                        notifyItemRemoved(getAdapterPosition());
                        dialog.dismiss();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
