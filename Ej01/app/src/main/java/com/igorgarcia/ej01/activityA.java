package com.igorgarcia.ej01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class activityA extends ActionBarActivity {

    private final String  DATA = "data";
    private String op=null;
    private Double  result=0.0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);


        if (savedInstanceState!=null){
            String DATA=savedInstanceState.getString(DATA);
            Log.d("Change","Activity a oncreate save");
        }




        Button btnOpenB =(Button) findViewById(R.id.btnOpenB);
        Button btnClose =(Button) findViewById(R.id.btnCerrar);

        btnOpenB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Change", "ActivityA onclick()");

              Intent openB=new Intent( activityA.this,ActivityB.class);
                startActivity(openB);
            }
        });



        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Change", "ActivityA close()");
                finish();
            }
        });
        Log.d("Change","activityA on create");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Change","activityA on start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Change", "activityA on REStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Change","activityA on Resume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Change","activityA on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        //si estamos escuchando al GPS o a otros sercvicios, es el momento de dejar de escuchar

       

        Log.d("Change","activityA on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Change","activityA on Destroy");
    }


 @Override
 protected void OnRestoreInstanceState(Bundle savedInstance){
     super.onRestoreInstanceState(savedInstance);
     Log.d("Change", "mensaje");
     this.restoreState(savedInstance);
 }



@Override
protected void onSaveInstanceState(Bundle outState){
    Log.d("Change","ActivityA onSaveInstanceState()");

    outState.putString( DATA ,"datos");
    super.onSaveInstanceState(outState);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity, menu);
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
