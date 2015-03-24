package com.igorgarcia.intents.receibers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConectionReceiver extends BroadcastReceiver {


    private final String RECEIVER="RECEIBER";


    public ConectionReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast

       Log.d(RECEIVER, "ConnectionReceiver onReceive()");
        Log.d(RECEIVER,"ACTION" + intent.getAction());

        if (intent.getAction().equals(intent.ACTION_AIRPLANE_MODE_CHANGED) ){
            Log.d(RECEIVER,"ACTION" + intent.getAction());
        }else if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)  ){
            Log.d(RECEIVER,"ACTION" + intent.getAction());
            
        }


    }
}
