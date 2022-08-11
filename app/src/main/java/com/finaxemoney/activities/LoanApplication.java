package com.finaxemoney.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.finaxemoney.R;
import com.finaxemoney.model.TokenResponse;
import com.finaxemoney.model.TransactionPojo;
import com.finaxemoney.model.USER;
import com.finaxemoney.network.RetrofitClientt;
import com.finaxemoney.tools.CONSTANT;
import com.finaxemoney.tools.ExtraOperations;
import com.gocashfree.cashfreesdk.CFPaymentService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;

public class LoanApplication extends AppCompatActivity   {
    private static final String TAG = LoanApplicationAuth.class.getSimpleName();

    MaterialButton payNow;
    TextView amount;
 USER user;
 String token , orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        payNow = findViewById(R.id.paynowBtn);
        amount = findViewById(R.id.amount);
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ///  addTransactionDetails();
               // startPayment();
              //  StartPayment();
          doPayment();
            }
        });
        GETTOKEN();
/////////////////////////////
        getAmount();
    }



    private void addTransactionDetails(String paymentid, String mode) {
        if (new ExtraOperations().haveNetworkConnection(getApplicationContext())) {
            Date myDate = new Date();
            System.out.println(myDate);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
            TransactionPojo pojo = new TransactionPojo();
            pojo.setAmount(299);
            pojo.setDate(date);
            pojo.setId(paymentid);
            pojo.setRemark("Fee Paid");
            pojo.setUser_id(FirebaseAuth.getInstance().getUid());
            pojo.setWallet(mode);
            pojo.setType("Fee");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(CONSTANT.TRANSACTION)
                    .child(FirebaseAuth.getInstance().getUid());
            reference.push().setValue(pojo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            });
        }
    }



    void getAmount(){

        FirebaseDatabase.getInstance().getReference().child(CONSTANT.USER)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try{
                            user = dataSnapshot.getValue(USER.class);
                            if(user.getAmount()!=null){
                                amount.setText(user.getAmount());
                            }


                        }catch (Exception e){
                            Log.d("Error", "onDataChange: "+e);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }


    public void startPayment() {

        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            if(user!=null) {
                JSONObject options = new JSONObject();
                options.put("name", user.getName());
                options.put("description", "Service Charges");
                //You can omit the image option to fetch the image from dashboard
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                options.put("currency", "INR");
                options.put("amount", "29900");

                JSONObject preFill = new JSONObject();
                preFill.put("email", user.getEmail());
                preFill.put("contact", user.getPhone());

                options.put("prefill", preFill);

                co.open(activity, options);
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")



    public void StartPayment() {

        MaterialButton  close,no;
        final Dialog dialog;
        dialog = new Dialog(LoanApplication.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.diologAnimatioLocation;
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finishAffinity();
//            }
//        });
//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
        dialog.show();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); //Same request code for all payment APIs.
         Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE); Log.d(TAG, "API Response : ");
        // Prints all extras. Replace with app logic.
        if (data != null) { Bundle bundle = data.getExtras();
        if (bundle != null) {
            String status="",OId="", PaymentMode="";
            for (String key : bundle.keySet()) {
                if (bundle.getString(key) != null) {
                    Log.d(TAG, key + " : " + bundle.getString(key));
                    if(key.equals("paymentMode")){
                        PaymentMode= bundle.getString(key);
                    }


                }
            }
            status= bundle.getString("txStatus").toString();
            if(status.equals("SUCCESS")){
                PaymentMode= bundle.getString("paymentMode").toString();
                OId= bundle.getString("orderId");
                        addTransactionDetails(OId,PaymentMode);
            }



        }
        }
    }

     void doPayment(){
        if(user!=null) {
            String stage = "PROD";
            String appId =  RetrofitClientt.APPID;
            String orderAmount = "299";
            String orderNote = "Application Fee";
            String customerName = ""+user.getName();
            String customerPhone = ""+user.getPhone();
            String customerEmail = ""+user.getEmail();

            Map<String, String> params = new HashMap<>();

            params.put(PARAM_APP_ID, appId);
            params.put(PARAM_ORDER_ID, orderId);
            params.put(PARAM_ORDER_AMOUNT, orderAmount);
            params.put(PARAM_ORDER_NOTE, orderNote);
            params.put(PARAM_CUSTOMER_NAME, customerName);
            params.put(PARAM_CUSTOMER_PHONE, customerPhone);
            params.put(PARAM_CUSTOMER_EMAIL, customerEmail);


            for (Map.Entry entry : params.entrySet()) {
                Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
            }

            CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
            cfPaymentService.setOrientation(0);

            cfPaymentService.doPayment(LoanApplication.this, params, token, stage, "#784BD2", "#FFFFFF", false);
        }
      }




    void GETTOKEN() {
        JSONObject jsonObject = new JSONObject();
        try {

            orderId = "OID"+System.currentTimeMillis();
            jsonObject.put("orderId", orderId);
            jsonObject.put("orderAmount",299);
            jsonObject.put("orderCurrency","INR");


        }catch (Exception e){
            e.printStackTrace();
        }
        JsonParser jsonParser = new JsonParser();
        Call<TokenResponse> call = RetrofitClientt.getInstance().getApi().getToken((JsonObject) jsonParser.parse(jsonObject.toString()));
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                if(response.isSuccessful()){

                    Log.d("RES", "Success" + response.body().toString());
                        token= response.body().getCftoken();
                }
                else{

                    Log.d("RES", "Error" + response.errorBody());

                }

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.d("RES", "onFailure: "+ t);
            }
        });
    }
//    public void onClick(View view) {
//
//
//        /*
//         * stage allows you to switch between sandboxed and production servers
//         * for CashFree Payment Gateway. The possible values are
//         *
//         * 1. TEST: Use the Test server. You can use this service while integrating
//         *      and testing the CashFree PG. No real money will be deducted from the
//         *      cards and bank accounts you use this stage. This mode is thus ideal
//         *      for use during the development. You can use the cards provided here
//         *      while in this stage: https://docs.cashfree.com/docs/resources/#test-data
//         *
//         * 2. PROD: Once you have completed the testing and integration and successfully
//         *      integrated the CashFree PG, use this value for stage variable. This will
//         *      enable live transactions
//         */
//
//
//        //Show the UI for doGPayPayment and phonePePayment only after checking if the apps are ready for payment
//        if (view.getId() == R.id.phonePe_exists) {
//            Toast.makeText(
//                    LoanApplication.this,
//                    CFPaymentService.getCFPaymentServiceInstance().doesPhonePeExist(LoanApplication.this, stage)+"",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        } else if (view.getId() == R.id.gpay_ready) {
//            CFPaymentService.getCFPaymentServiceInstance().isGPayReadyForPayment(LoanApplication.this, new GooglePayStatusListener() {
//                @Override
//                public void isReady() {
//                    Toast.makeText(LoanApplication.this, "Ready", Toast.LENGTH_SHORT).show();
//                }
//                @Override
//                public void isNotReady() {
//                    Toast.makeText(LoanApplication.this, "Not Ready", Toast.LENGTH_SHORT).show();
//                }
//            });
//            return;
//        }
//
//        /*
//         * token can be generated from your backend by calling cashfree servers. Please
//         * check the documentation for details on generating the token.
//         * READ THIS TO GENERATE TOKEN: https://bit.ly/2RGV3Pp
//         */
//
//
//
//        /*
//         * appId will be available to you at CashFree Dashboard. This is a unique
//         * identifier for your app. Please replace this appId with your appId.
//         * Also, as explained below you will need to change your appId to prod
//         * credentials before publishing your app.
//         */
//
//        switch (view.getId()) {
//
//            /***
//             * This method handles the payment gateway invocation (web flow).
//             *
//             * @param context Android context of the calling activity
//             * @param params HashMap containing all the parameters required for creating a payment order
//             * @param token Provide the token for the transaction
//             * @param stage Identifies if test or production service needs to be invoked. Possible values:
//             *              PROD for production, TEST for testing.
//             * @param color1 Background color of the toolbar
//             * @param color2 text color and icon color of toolbar
//             * @param hideOrderId If true hides order Id from the toolbar
//             */
//            case R.id.web: {
//
////                 cfPaymentService.doPayment(LoanApplication.this, params, token, stage);
//                break;
//            }
//            /***
//             * Same for all payment modes below.
//             *
//             * @param context Android context of the calling activity
//             * @param params HashMap containing all the parameters required for creating a payment order
//             * @param token Provide the token for the transaction
//             * @param stage Identifies if test or production service needs to be invoked. Possible values:
//             *              PROD for production, TEST for testing.
//             */
//            case R.id.upi: {
////                                cfPaymentService.selectUpiClient("com.google.android.apps.nbu.paisa.user");
//                cfPaymentService.upiPayment(LoanApplication.this, params, token, stage);
//                break;
//            }
//            case R.id.amazon: {
//                cfPaymentService.doAmazonPayment(LoanApplication.this, params, token, stage);
//                break;
//            }
//            case R.id.gpay: {
//                cfPaymentService.gPayPayment(LoanApplication.this, params, token, stage);
//                break;
//            }
//            case R.id.phonePe: {
//                cfPaymentService.phonePePayment(LoanApplication.this, params, token, stage);
//                break;
//            }
//        }
//    }

}