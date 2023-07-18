package com.studentzone.Doctor_Classes.Doctor_Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.studentzone.R;

import java.util.ArrayList;

public class ModelRecyclerView_adapter extends RecyclerView.Adapter<ModelRecyclerView_adapter.ViewHolder> {//Adapter
ArrayList<Model>arrayList=new ArrayList<>(); //array list
    private ArrayList<Model> dataList;

    public ModelRecyclerView_adapter(ArrayList<Model> dataList) {
        this.dataList = dataList;
    }

    Context context;
    CardView cv_subjects;
public ModelRecyclerView_adapter(Context context, ArrayList<Model>arrayList){

    this.context=context;
    this.arrayList=arrayList;

}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//onCreate

        View view= LayoutInflater.from(context).inflate(R.layout.activity_doctor_model_subject,parent,false);//holder have model
    ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//Bindeer
holder.tv1_n.setText(arrayList.get(position).getSubjectName());
holder.tv2_c.setText(arrayList.get(position).getCodeName());


    holder.crv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/11lKL5ogDy4v_dM8uePHrw4v-SzI9Jx8d?usp=sharing"));
            context.startActivity(intent);


        }
    });
}


    @Override
    public int getItemCount() {//getIndexItem
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//holder
        ImageView imgSub;
        TextView tv1_n,tv2_c;
        CardView crv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1_n=itemView.findViewById(R.id.activity_doctor_subject_tv_sub_name);//store inflate in object
            tv2_c=itemView.findViewById(R.id.activity_doctor_subject_tv_sub_code);
            crv=itemView.findViewById(R.id.activity_doctor_subject_cv);

        }
    }


}
