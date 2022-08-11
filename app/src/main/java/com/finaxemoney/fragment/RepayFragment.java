package com.finaxemoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.finaxemoney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepayFragment extends Fragment {

    public RepayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.frag_repayment, container,false);

        return view;
    }
}
