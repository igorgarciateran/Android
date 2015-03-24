package com.igorgarcia.intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;


public class MainActivity extends ActionBarActivity {


    private static final int Opcion1 = 1;
    private static final int CamaraFoto = 2;

    public static final String  Menssage = "Dato";



    Button btnEnviar = null;
    EditText txtEntrada = null;
    Button btnFoto = null;
    ImageView MiFoto = null;
    EditText txtResultado = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = (Button) findViewById(R.id.btnadd);
        txtEntrada = (EditText) findViewById(R.id.txtentrada);
        btnFoto = (Button) findViewById(R.id.btnFoto);
        MiFoto = (ImageView) findViewById(R.id.ImgWFoto);
        txtResultado = (EditText) findViewById(R.id.txtResultado);



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Message =txtEntrada.getText().toString();
                if (Message.length() > 0) {
                    Intent intento1 = new Intent(MainActivity.this, Resultado.class);
                    intento1.putExtra(Menssage, txtEntrada.getText().toString());
                    startActivityForResult(intento1, Opcion1);
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, getResources().getString(R.string.TextoAPasar), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sacar una foto
                Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intento1.resolveActivity(getPackageManager()) !=null){
                    startActivityForResult(intento1, CamaraFoto);
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, getResources().getString(R.string.TextoAPasar), Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case (Opcion1):

                if (resultCode == Activity.RESULT_OK) {

                    // txtEntrada.setText( );
                    String Datos = data.getStringExtra(Menssage);
                    txtResultado.setText(Datos);
                    break;
                }

            case (CamaraFoto):

                if (resultCode == Activity.RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap=(Bitmap) extras.get("data");
                    MiFoto.setImageBitmap(imageBitmap);
                }

                break;

            default:
                break;

        }

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
