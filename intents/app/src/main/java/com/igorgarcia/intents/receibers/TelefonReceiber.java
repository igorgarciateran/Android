package com.igorgarcia.intents.receibers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TelefonReceiber extends BroadcastReceiver {
    public TelefonReceiber() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d(RECEIVER, "ConnectionReceiver onReceive()");
        Log.d(RECEIVER,"ACTION" + intent.getAction());





    }
}
