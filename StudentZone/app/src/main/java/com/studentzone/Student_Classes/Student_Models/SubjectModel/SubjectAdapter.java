package com.studentzone.Student_Classes.Student_Models.SubjectModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.ArrayList;
import java.util.Locale;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{

    Context context ;
    ArrayList<SubjectModel> arrayList2 =new ArrayList<>();


    public SubjectAdapter(Context context, ArrayList<SubjectModel> arrayList2) {
        this.context=context;
        this.arrayList2 = arrayList2;
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

        String  abbreviation = "",courseName = model.getSubjectName();
        String[] words = courseName.split(" ");
        for (String word : words) {
            char firstLetter = word.charAt(0);
            abbreviation += firstLetter;
        }
        holder.tv_3.setText(abbreviation.toUpperCase(Locale.ROOT));

    }



    @Override
    public int getItemCount() {
        return arrayList2.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {//holder
        ImageButton IM;
        TextView tv1_n,tv2_c,tv_3;
        CardView crv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1_n=itemView.findViewById(R.id.activity_student_subject_tv_sub_name);//store inflate in object
            tv2_c=itemView.findViewById(R.id.activity_student_subject_tv_sub_code);
            crv=itemView.findViewById(R.id.activity_student_subject_iv_cv);
            tv_3=itemView.findViewById(R.id.activity_student_subject_tv_sn);
            IM=itemView.findViewById(R.id.activity_student_subject_ibtn_more);


        }
    }
}
