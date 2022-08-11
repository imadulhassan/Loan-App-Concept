package com.finaxemoney.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.finaxemoney.R;
import com.finaxemoney.fragment.LoanFragment;
import com.finaxemoney.fragment.RepayFragment;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout loantab, repaytab, profiletab;
    ImageView  iv_loan, iv_repay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.into_activity);
        init();

    }


    void init(){
        //tabs
         loantab= findViewById(R.id.loan);
         repaytab= findViewById(R.id.repayment);
         profiletab= findViewById(R.id.profile);

         //
        iv_loan= findViewById(R.id.iv_loan);
        iv_repay= findViewById(R.id.iv_repayment);

        loantab.setOnClickListener(this);
        repaytab.setOnClickListener(this);
        profiletab.setOnClickListener(this);

        loadFragment(new LoanFragment());
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
                fragment).commit();
//        getSupportFragmentManager().beginTransaction().
//                replace(R.id.framelayout,fragment).commit();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.loan :
           iv_loan.setImageResource(R.drawable.loanactivated);
           iv_repay.setImageResource(R.drawable.repayment);
                         loadFragment(new LoanFragment());

                break;

            case R.id.profile:
                startActivity(new Intent(getApplicationContext() , OtpActivity.class));
                overridePendingTransition(android.R.anim.fade_in ,android.R.anim.fade_out);
                break;

            case R.id.repayment :
                iv_loan.setImageResource(R.drawable.loan);
                iv_repay.setImageResource(R.drawable.repaymentactive);

                loadFragment(new RepayFragment());

                   break;
        }

    }
}
