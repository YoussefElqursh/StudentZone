package com.studentzone.Student_Classes.Student_Models.RegestrationModel;

import static java.sql.Types.NULL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;


import java.util.ArrayList;
import java.util.Locale;

public class SubjectRegestrationAdapter extends RecyclerView.Adapter<SubjectRegestrationAdapter.ViewHolder> {
    ArrayList<SubjectRegestrationModel>arrayList=new ArrayList<>(); //array list
   private    final ArrayList<Integer> Course_id =new ArrayList<>();
    Context context;
    My_DB Db;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;

    public SubjectRegestrationAdapter(Context context, ArrayList<SubjectRegestrationModel>arrayList){
        this.context=context;
        this.arrayList=arrayList;
        Db = new My_DB(context);
        this.bottomSheetDialog = new BottomSheetDialog(context);



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_student_model_regestration,parent,false);//holder have model
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv1_n.setText(arrayList.get(position).getSubjectName());
        holder.tv2_c.setText(arrayList.get(position).getCodeName());


        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.itemView.startAnimation(animation);


        SubjectRegestrationModel model = arrayList.get(position);

        String  abbreviation = "",courseName = model.getSubjectName();
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }
        holder.tv_3.setText(abbreviation.toUpperCase(Locale.ROOT));


        holder.CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                My_DB my_db=new My_DB(context);
                int ID_Course=my_db.get_Id_course_by_CourseName(model.getSubjectName());
                SharedPreferences preferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
                String StudentId = preferences.getString("id", "");
                int S_id = Integer.parseInt(StudentId);

                if (isChecked == true) {
                    if (my_db.SubjectDuplicated(S_id, ID_Course)) {
                        Toast.makeText(context, "Subject already registered!", Toast.LENGTH_SHORT).show();
                        buttonView.setChecked(false);
                        System.out.println("SubjectDuplicated");
                    } else if (my_db.subject_have_Prerequest(model.getSubjectName())) {
                        int pre_id = my_db.getPreRequestIdBy_Name(model.getSubjectName());
                        System.out.println("prepre :"+pre_id);
                        if (my_db.pre_thereExist_inEnrollment_course_id(pre_id)) {

                            if (my_db.getSubjectDegree_Id(pre_id) != NULL) {
                                if (my_db.getSubjectDegree_Id(pre_id) >= 50) {
                                    Toast.makeText(context, "You have succeeded in the prerequisite " + my_db.get_course_by_Course_Id(pre_id) + ".", Toast.LENGTH_LONG).show();
                                    Course_id.add(ID_Course);
                                } else {
                                    Toast.makeText(context, "You have failed in the prerequisite " + my_db.get_course_by_Course_Id(pre_id) + ".", Toast.LENGTH_LONG).show();
                                    buttonView.setChecked(false);
                                }
                            } else {
                                Toast.makeText(context, "You don't have a degree in the prerequisite " + my_db.get_course_by_Course_Id(pre_id) + ".", Toast.LENGTH_LONG).show();
                                buttonView.setChecked(false);
                            }
                        } else {
                            Toast.makeText(context, "You should register the prerequisite " + my_db.get_course_by_Course_Id(pre_id) + ".", Toast.LENGTH_LONG).show();
                            buttonView.setChecked(false);
                        }
                    } else {
                        Course_id.add(ID_Course);
                    }
                } else {
                    Course_id.remove(Integer.valueOf(ID_Course));
                }

            }
        });



    }



public ArrayList<Integer> getCourse_id(){
        return Course_id;

    }





    @Override
    public int getItemCount() {
        return arrayList.size();
    }//Adapter





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {//holder
        ImageButton IM;

        TextView tv1_n,tv2_c,tv_3;
        CheckBox CB;
        CardView crv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1_n=itemView.findViewById(R.id.activity_student_registration_tv_sub_name);//store inflate in object
            tv2_c=itemView.findViewById(R.id.activity_student_registration_tv_sub_code);
            crv=itemView.findViewById(R.id.activity_student_Regestration_cv);
            CB=itemView.findViewById(R.id.activity_student_subject_tv_sub_state);
            tv_3=itemView.findViewById(R.id.activity_student_registration_tv_sn);
            IM=itemView.findViewById(R.id.activity_student_registration_ibtn_info);
            IM.setOnClickListener((View.OnClickListener) this);



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

        @Override
        public void onClick(View view) {
            SubjectRegestrationModel course = arrayList.get(getAdapterPosition());

            showDetailsBottomSheet(course.SubjectName,course.CodeName);

        }
    }


}
