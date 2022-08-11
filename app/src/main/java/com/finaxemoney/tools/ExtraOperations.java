package com.finaxemoney.tools;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ExtraOperations {
    public static String md5(String str) {
        final String MD5 = "MD5";
        try {
            MessageDigest instance = MessageDigest.getInstance(MD5);
            instance.update(str.getBytes());
            byte msgBytes[] = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : msgBytes) {
                String toHexString = Integer.toHexString(b & 255);
                while (toHexString.length() < 2) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("0");
                    stringBuilder2.append(toHexString);
                    toHexString = stringBuilder2.toString();
                }
                stringBuilder.append(toHexString);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException str2) {
            str2.printStackTrace();
            return "";
        }
    }

    public static void openAppRating(Context context) {
        String packageName = context.getPackageName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("market://details?id=");
        stringBuilder.append(packageName);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
        int i = 0;
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
//                intent.addFlags(268435456);
//                intent.addFlags(2097152);
//                intent.addFlags(67108864);
                intent.setComponent(componentName);
                context.startActivity(intent);
                i = 1;
                break;
            }
        }
        if (i == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("https://play.google.com/store/apps/details?id=");
            stringBuilder.append(packageName);
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
        }
    }

    public void showError(Context context, TextInputEditText textInputEditText) {
        textInputEditText.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.bounce_interpolator));
    }

    @SuppressLint("WrongConstant")
    public boolean haveNetworkConnection(Context context) {
        Object obj = null;
        Object obj2 = null;
        for (NetworkInfo networkInfo : ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo()) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                obj = 1;
            }
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                obj2 = 1;
            }
        }
        if (obj == null && obj2 == null) {
            return false;
        }
        return true;
    }

    public void launchPUBGMobile(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.tencent.ig");
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        } else {
            Toast.makeText(context, "PUBGMobile is Not Installed", Toast.LENGTH_SHORT).show();
        }
    }
}
