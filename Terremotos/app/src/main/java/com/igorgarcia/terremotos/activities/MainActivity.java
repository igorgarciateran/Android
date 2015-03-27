package com.igorgarcia.terremotos.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.igorgarcia.terremotos.Model.EarthQuake;
import com.igorgarcia.terremotos.R;
import com.igorgarcia.terremotos.Tasks.DownloadEarthQuakeTask;


public class MainActivity extends ActionBarActivity implements DownloadEarthQuakeTask.AddEarthQuakeInterface {
private int PREFS_ACTIVITY=1;


    private String EARTHQUAKE = "EARTHQUAKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadEarthQuakes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


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

            Intent prefsIntent = new Intent (this,SettingsActivity.class);
            startActivityForResult(prefsIntent,PREFS_ACTIVITY);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //aqui habria que poner el codigo si queremos hacer algo cuando se cierren las settings

    }*/


    @Override
    public void AddEarthQuake(EarthQuake earthquake) {

  /*      //Solo dibujamos los mayores que el filtro
        double minMag=Double.parseDouble(Prefs.getString(getString(R.string.opcion3Key), ""));

        if (earthquake.getMagnitud()>=minMag) {

            earthQuakes.add(0, earthquake);
            //aa.notifyDataSetChanged();

            aa2.notifyDataSetChanged();

        }*/
        // esto ya no hace falta




    }

    @Override
    public void NotifyTotal(int Total) {
        String msg= getString(R.string.Mensaje,Total);
        Toast toast=  Toast.makeText( this,msg + Total, Toast.LENGTH_LONG);
    }

    private void downloadEarthQuakes(){
        DownloadEarthQuakeTask task =new DownloadEarthQuakeTask(this ,this);
        task.execute(getString(R.string.earth_quakes_url));
    }




}
