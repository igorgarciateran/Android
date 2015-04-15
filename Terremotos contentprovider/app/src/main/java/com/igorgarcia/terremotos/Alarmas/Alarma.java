package com.igorgarcia.terremotos.Alarmas;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;
import com.igorgarcia.terremotos.servicios.DownloadEarthQuakeService;

/**
 * Created by cursomovil on 31/03/15.
 */
public class Alarma {


    public static void setAlarm(Context contexto, long  tiempo) {
        //pone una Alarma no repetitiva

        //	Get	a	reference	to	the	Alarm	Manager
        AlarmManager alarmManager = (AlarmManager)contexto.getSystemService(Context.ALARM_SERVICE);

        //	Set	the	alarm	to	wake	the	device	if	sleeping.
        int alarmType = AlarmManager.RTC;


        //	Trigger	the	device	in seconds.
        long timeOrLengthofWait = tiempo * 60 *60* 1000;


        //	Create	a	Pending	Intent	that	will	broadcast	and	action

        Intent intentToFire = new Intent(contexto, DownloadEarthQuakeService.class);

        PendingIntent alarmIntent = PendingIntent.getService(contexto, 0, intentToFire, 0);


        //	Set	the	alarm
        alarmManager.set(alarmType, timeOrLengthofWait, alarmIntent);

        Log.d("EARTHQUAKE"," Alarma  puesta" + tiempo );

    }


    public static void setRepeatingAlarm(Context contexto,int tiempo, boolean exacto) {

        //Get	a	reference	to	the	Alarm	Manager
        AlarmManager alarmManager = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);


        //Set	the	alarm	to	wake	the	device	if	sleeping.
        int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;


        //Schedule	the	alarm	to	repeat	every	half	hour.
        long timeOrLengthofWait = tiempo *1000 ;//AlarmManager.INTERVAL_FIFTEEN_MINUTES/30;


        //Create	a	Pending	Intent	that	will	broadcast	and	action
        Intent intentToFire = new Intent(contexto, DownloadEarthQuakeService.class);

        PendingIntent alarmIntent = PendingIntent.getService(contexto,  0, intentToFire, 0);

        //Wake	up	the	device	to	fire	an	alarm	in	half	an	hour,	and	every
        //half-hour	after	that.
        if (exacto){
            alarmManager.setRepeating(alarmType,0,timeOrLengthofWait,alarmIntent);
            Log.d("EARTHQUAKE"," Alarma repetitiva puesta " +tiempo );
        }else {
            alarmManager.setInexactRepeating(alarmType, 0, timeOrLengthofWait, alarmIntent);
            Log.d("EARTHQUAKE"," Alarma repetitiva inexacta puesta " +tiempo );
        }
    }



    public static void desactivarAlarma(Context contexto){

        //	Get	a	reference	to	the	Alarm	Manager
        AlarmManager alarmManager = (AlarmManager)contexto.getSystemService(Context.ALARM_SERVICE);

        //Create	a	Pending	Intent	that	will	broadcast	and	action
        Intent intentToFire = new Intent(contexto, DownloadEarthQuakeService.class);

        PendingIntent alarmIntent = PendingIntent.getService(contexto, 0, intentToFire, 0);

        alarmManager.cancel(alarmIntent);

        Log.d("EARTHQUAKE"," Alarma quitada " );

    }


    public static void ActualizarAlarma (Context contexto, long intervalo){

        setAlarm(contexto,intervalo);

    }







}
