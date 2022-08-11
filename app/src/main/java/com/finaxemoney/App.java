package com.finaxemoney;


import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * App extending Application, is used to save some Lists and Objects with Application Context.
 **/


public class App extends Application {

    // Application Context
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        // set App Context
        context = this.getApplicationContext();
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Checkout.preload(getApplicationContext());


    }



}


