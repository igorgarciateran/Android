package com.igorgarcia.intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Resultado extends ActionBarActivity {

    Button BtnSend=null;
    EditText Texto=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        BtnSend=(Button) findViewById(R.id.btnadd);
        Texto = (EditText) findViewById(R.id.txtentrada);

ProcessIntent();

      /*  Bundle bundle = getIntent().getExtras();

        if(bundle.getString("Dato")!= null){
            //Texto.setText();
            String Dato=bundle.getString("Dato");
        }
*/


        BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Message =Texto.getText().toString();
                if (Message.length() > 0) {
                    Intent intento1 = new Intent();
                    intento1.putExtra(MainActivity.Menssage, Message);

                    setResult(RESULT_OK, intento1);
                    finish();
                } else {
                    Toast toast = Toast.makeText(Resultado.this, getResources().getString(R.string.TextoAPasar), Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
    }

    private void ProcessIntent(){
        Intent data=getIntent();
        String Message=data.getStringExtra(MainActivity.Menssage);
        Texto.setText(Message);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultado, menu);
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
