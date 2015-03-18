package com.igorgarcia.calculadora;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.sql.Array;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Button btnx0 = null;
    Button btnx1 = null;
    Button btnx2 = null;
    Button btnx3 = null;
    Button btnx4 = null;
    Button btnx5 = null;
    Button btnx6 = null;
    Button btnx7 = null;
    Button btnx8 = null;
    Button btnx9 = null;

    Button btnx10 = null;
    Button btnx11 = null;

    ArrayList<Button> Botones=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnx0= (Button) findViewById(R.id.btn0);
        btnx1= (Button) findViewById(R.id.btn1);
        btnx2= (Button) findViewById(R.id.btn2);
        btnx3= (Button) findViewById(R.id.btn3);
        btnx4= (Button) findViewById(R.id.btn4);
        btnx5= (Button) findViewById(R.id.btn5);
        btnx6= (Button) findViewById(R.id.btn6);
        btnx7= (Button) findViewById(R.id.btn7);
        btnx8= (Button) findViewById(R.id.btn8);
        btnx9= (Button) findViewById(R.id.btn9);
        btnx10= (Button) findViewById(R.id.btn10);
        btnx11= (Button) findViewById(R.id.btn11);

        Botones.add(btnx0);
        Botones.add(btnx1);
        Botones.add(btnx2);
        Botones.add(btnx3);
        Botones.add(btnx4);
        Botones.add(btnx5);
        Botones.add(btnx6);
        Botones.add(btnx7);
        Botones.add(btnx8);
        Botones.add(btnx9);
        Botones.add(btnx10);
        Botones.add(btnx11);

for (int i=0 ;i< Botones.size()-1;i++){
    addEvent(Botones[i]);
}

    }

private void addEvent(){
this.add
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
