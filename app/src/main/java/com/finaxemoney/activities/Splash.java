package com.finaxemoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.finaxemoney.R;
import com.finaxemoney.tools.CONSTANT;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mAuth=FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();

        ImageView imageView = findViewById(R.id.image);
        final Animation animShake = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animShake.setDuration(3000);
        imageView.startAnimation(animShake);


                if(mCurrentUser!=null) {
                    CheckAccountDetails();


                }else {

                    Intent mainIntent = new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(mainIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();

                }
         }



         void CheckAccountDetails(){
             FirebaseDatabase.getInstance().getReference().child(CONSTANT.USER).child(FirebaseAuth.getInstance().getUid())
                     .addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             if(dataSnapshot.exists()){

                                 CheckTransctionDetails();

                             }else{
                                 Intent mainIntent = new Intent(getApplicationContext(), Registration.class);
                                 startActivity(mainIntent);
                                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                 finish();


                             }

                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });

         }




    void CheckTransctionDetails(){
        FirebaseDatabase.getInstance().getReference().child(CONSTANT.TRANSACTION).child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainIntent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();

                        }else{

                            Intent mainIntent = new Intent(getApplicationContext(), LoanApplication.class);
                            startActivity(mainIntent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }


}