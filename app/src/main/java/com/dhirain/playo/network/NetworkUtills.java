package com.dhirain.playo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dhirain.playo.NewsGoApp;


/**
 * Created by Dhirain Jain on 11-10-2017.
 */

public class NetworkUtills {

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) NewsGoApp.singleton().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
