package com.studentzone.Admin_Calsses.Admin_Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.studentzone.R;

public class AdminNewStudentAccountFragment extends Fragment {

    EditText et_studet_name;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AdminNewStudentAccountFragment() {
        // Required empty public constructor
    }

    public static AdminNewStudentAccountFragment newInstance(String param1, String param2) {
        AdminNewStudentAccountFragment fragment = new AdminNewStudentAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_admin_new_student_account, container, false);
    }
}