package com.igorgarcia.terremotos.Alarmas;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by cursomovil on 31/03/15.
 */
public class alarma {


    private void setAlarm(Context contexto,int tiempo) {
        //pone una alarma no repetitiva

        //	Get	a	reference	to	the	Alarm	Manager
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //	Set	the	alarm	to	wake	the	device	if	sleeping.
        int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;


        //	Trigger	the	device	in	10	seconds.
        long timeOrLengthofWait = tiempo *1000;


        //	Create	a	Pending	Intent	that	will	broadcast	and	action
        String ALARM_ACTION = "ALARM_ACTION";
        Intent intentToFire = new Intent(ALARM_ACTION);
        PendingIntent alarmIntent = PendingIntent.getService(contexto, 0, intentToFire, 0);


        //	Set	the	alarm
        alarmManager.set(alarmType, timeOrLengthofWait, alarmIntent);
        //alarmManager.cancel(alarmIntent);

    }


    private void setRepeatingAlarm(Context contexto,int tiempo, boolean exacto) {

        //Get	a	reference	to	the	Alarm	Manager
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        


        //Set	the	alarm	to	wake	the	device	if	sleeping.
        int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;


        //Schedule	the	alarm	to	repeat	every	half	hour.
        long timeOrLengthofWait = tiempo *1000 ;//AlarmManager.INTERVAL_FIFTEEN_MINUTES/30;


        //Create	a	Pending	Intent	that	will	broadcast	and	action
        String ALARM_ACTION = "ALARM_ACTION";

        Intent intentToFire = new Intent(ALARM_ACTION);

        PendingIntent alarmIntent = PendingIntent.getService(contexto, 0, intentToFire, 0);


        //Wake	up	the	device	to	fire	an	alarm	in	half	an	hour,	and	every
        //half-hour	after	that.
        if (exacto){
            alarmManager.setRepeating(alarmType,timeOrLengthofWait,timeOrLengthofWait,alarmIntent);
        }else {
            alarmManager.setInexactRepeating(alarmType, timeOrLengthofWait, timeOrLengthofWait, alarmIntent);
        }


    }



    private void DesactivarAlarma(Context contexto,){

        //	Get	a	reference	to	the	Alarm	Manager
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //Create	a	Pending	Intent	that	will	broadcast	and	action
        String ALARM_ACTION = "ALARM_ACTION";

        Intent intentToFire = new Intent(ALARM_ACTION);

        PendingIntent alarmIntent = PendingIntent.getService(contexto, 0, intentToFire, 0);

        alarmManager.cancel(alarmIntent);
    }


}
