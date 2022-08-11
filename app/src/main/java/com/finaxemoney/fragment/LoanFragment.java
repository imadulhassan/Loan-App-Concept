package com.finaxemoney.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finaxemoney.R;
import com.finaxemoney.activities.OtpActivity;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends Fragment {

    MaterialButton applyBtn;


    public LoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.frag_loadinto, container,false);
        inti(view);
        return view;
    }

    void inti(View view){
        applyBtn = view.findViewById(R.id.applybtn);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), OtpActivity.class);
                startActivity(mainIntent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
    }
}
