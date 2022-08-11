package com.finaxemoney.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.finaxemoney.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {

    private MaterialButton send;
    private TextView errorcode;
    public TextInputEditText phone,code;
    String phoneNumber,verifyCode;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabase;
    private boolean mCodeSended = false;
    private Spinner codeSn;
    private TextInputEditText cCode;
    private String countryCode;
    private CheckBox chkAgreeTo;
    TextView textAgree , privacyPolicy;


    private String[] codeStr;
    private List<String> codeList;
    private ArrayAdapter<String> codeAdapter;
    private TextInputLayout countryCodeInput;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp );

        mAuth= FirebaseAuth.getInstance();
        send = findViewById(R.id.send);
        errorcode = findViewById(R.id.error);
        phone = findViewById(R.id.mobileNumber);
        cCode = (TextInputEditText) findViewById(R.id.countryCode);
        countryCodeInput = (TextInputLayout) findViewById(R.id.countryCodeInput);
        codeSn = (Spinner) findViewById(R.id.codeSn);
        chkAgreeTo = (CheckBox) findViewById(R.id.chkAgreeTo);
        privacyPolicy= findViewById(R.id.privacypolicy);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        cCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.countryCode) {
//                    codeSn.performClick();
//                    countryCodeInput.setVisibility(View.GONE);
//                    cCode.setVisibility(View.GONE);
//                    codeSn.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        textAgree = findViewById(R.id.txtAgreeTo);
        codeSn.setPrompt("Pick your country code");
        codeStr = getResources().getStringArray(R.array.country_codes);
        codeList = new ArrayList<>(Arrays.asList(codeStr));
        codeAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, codeList);
        codeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        codeSn.setAdapter(codeAdapter);

        codeSn.setSelection(codeSn.getSelectedItemPosition(), false);
        codeSn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                cCode.setText(codeSn.getSelectedItem().toString());
                countryCode = codeSn.getSelectedItem().toString();
                codeSn.setVisibility(View.GONE);
                cCode.setVisibility(View.VISIBLE);
                countryCodeInput.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
                codeSn.setVisibility(View.GONE);
                cCode.setVisibility(View.VISIBLE);
                countryCodeInput.setVisibility(View.VISIBLE);
            }
        });
            textAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent(getApplicationContext(),TermActivity.class ));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                }
            });

//
//        Typeface typeface = getResources().getFont(R.font.myfirstfont);
//        textView.setTypeface(typeface);


        mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                if(e.getMessage()!=null) {
                    errorcode.setVisibility(View.VISIBLE);
                    errorcode.setText("Error" + e.getMessage());
                }
                 Log.d("error", "onCodeSent:" + e);
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("reg_auth", "onCodeSent:" + verificationId);
                errorcode.setText("");
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                mCodeSended=true;
                startActivity(new Intent(OtpActivity.this,CodeActivity.class).putExtra("id", mVerificationId));


                // [START_EXCLUDE]
                // Update UI
                //updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  phoneNumber =cCode.getText().toString()+ phone.getText().toString();
                phoneNumber ="+91"+ phone.getText().toString();

                Log.d("Code", "onClick: "+phoneNumber);
                //startActivity(new Intent(OtpActivity.this,CodeActivity.class));
                if (  validatemobilenumber() ) {
                     if (!chkAgreeTo.isChecked()) {

                         Toast.makeText(OtpActivity.this, "Please accept terms & condition.", Toast.LENGTH_LONG).show();

                    } else {
                        if (phoneNumber!=null) {
                            Toast.makeText(OtpActivity.this, phoneNumber, Toast.LENGTH_LONG).show();
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    phoneNumber,        // Phone number to verify
                                    60,                 // Timeout duration
                                    TimeUnit.SECONDS,   // Unit of timeout
                                    OtpActivity.this,               // Activity (for callback binding)
                                    mCallbacks );
                        }
                    }
                }


            }



        });

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),Privacy.class ));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
        });

    }



    public boolean validatemobilenumber() {
        if (this.phone.length() == 9 || this.phone.length() == 10 || this.phone.length() == 11 || this.phone.length() == 12 || this.phone.length() == 13) {
            return true;
        }
        this.phone.setError("Mobile Number should be of 9 to 13 Digits");
        return false;
    }

    public boolean validatecountrycode() {
        if (this.countryCode.length() >= 1) {
            return true;
        }
        this.cCode.setError("Pick your country code");
        return false;
    }



}
