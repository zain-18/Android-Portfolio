package com.hiddenskull.myapplication.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.ui.MainActivity;

public class ThirdFragment extends Fragment {
TextView starttextview;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_third, container, false);
        starttextview=view.findViewById(R.id.Start);
        starttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
