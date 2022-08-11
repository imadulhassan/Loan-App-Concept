package com.finaxemoney.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.finaxemoney.R;
import com.finaxemoney.tools.CONSTANT;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CodeActivity extends AppCompatActivity {
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private String mVerificationId,code;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
   TextInputEditText otpEditText;
   TextView resendOTP;
   TextView textError;
   MaterialButton verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        otpEditText = (TextInputEditText)  findViewById(R.id.otpEditText);
        resendOTP = (TextView) findViewById(R.id.resendOTP);
        textError = (TextView) findViewById(R.id.textError);

        verifyBtn =findViewById(R.id.next);

        mAuth= FirebaseAuth.getInstance();
        mVerificationId=getIntent().getStringExtra("id");

//        Typeface typeface = getResources().getFont(R.font.myfirstfont);
//        textView.setTypeface(typeface);


        mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                RegWithPhone(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("reg_auth", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                //mCodeSended=true;
                //startActivity(new Intent(CodeActivity.this,MainActivity.class));


                // [START_EXCLUDE]
                // Update UI
                //updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };



        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(CodeActivity.this,Registeration.class));
                code=otpEditText.getText().toString();
                if(code!=null) {
                   verifyCode();
                }
            }
        });
    }


    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
                  // ForceResendingToken from callbacks
    }
    // [END resend_verification]


    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        RegWithPhone(credential);
    }

    public void RegWithPhone(PhoneAuthCredential credential){
        // [START sign_in_with_phone]
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("REG_phone", "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            checkAccountType();
                        }
                        else {
                            // Sign in failed, display a message and update the UI
                            textError.setVisibility(View.VISIBLE);
                            textError.setText(task.getException()+"");
                            Log.w("REG_phone", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                // [START_EXCLUDE silent]
                                // mVerificationField.setError("Invalid code.");
                                // [END_EXCLUDE]
                            }
                            // [START_EXCLUDE silent]
                            // Update UI
                            // updateUI(STATE_SIGNIN_FAILED);
                            // [END_EXCLUDE]
                        }
                    }
                });
    }



    void checkAccountType(){
               DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child(CONSTANT.USER).child(FirebaseAuth.getInstance().getUid());
               mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      if(dataSnapshot.exists()){
                          Intent mainIntent=new Intent(getApplicationContext(), MainActivity.class);
                          mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                          startActivity(mainIntent);
                          finish();

                      }else{
                          Intent mainIntent=new Intent(getApplicationContext(),Registration .class);
                          mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                          startActivity(mainIntent);
                          finish();


                      }
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {
                      Log.d("Check User", "onCancelled: "+ databaseError);
                  }
              });
    }


    public void verifyCode() {
        try {
            InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String code1 = otpEditText.getText().toString();

        if (code1.length() == 6) {
            verifyPhoneNumberWithCode(mVerificationId, code);
        }
    }


    // [END sign_in_with_phone]
}
