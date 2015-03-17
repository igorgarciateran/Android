package com.igorgarcia.ej01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActivityB extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Button btnOpenB =(Button) findViewById(R.id.btnOpenB);
        Button btnClose =(Button) findViewById(R.id.btnCerrar);

        btnOpenB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Change", "ActivityB onclick()");

                Intent openA=new Intent( ActivityB.this,activityA.class);
                startActivity(openA);
            }
        });



        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Change", "ActivityB close()");
                finish();
            }
        });
        Log.d("Change","activityB on create");


    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Change", "activityB on start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Change", "activityB on REStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Change","activityB on Resume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Change","activityB on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Change","activityB on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Change","activityB on Destroy");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_b, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
