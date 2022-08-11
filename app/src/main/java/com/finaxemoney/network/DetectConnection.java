package com.finaxemoney.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DetectConnection {
//    public static boolean checkInternetConnection(Context context) {
//        // detect internet connection
//        ConnectivityManager con_manager = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (con_manager.getActiveNetworkInfo() != null
//                && con_manager.getActiveNetworkInfo().isAvailable()
//                && con_manager.getActiveNetworkInfo().isConnected()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static boolean checkInternetConnection(Context context) {
        boolean conntected = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                conntected = true;
            } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                conntected = true;
            } else {
                conntected = false;
            }
        }
        return conntected;
    }
}
