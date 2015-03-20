package com.igorgarcia.calculadora;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

import Listeners.NumberOnClickListener;
import Listeners.OperacionesOnClickListener;


public class MainActivity extends ActionBarActivity implements NumberOnClickListener {

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

    TextView Datos = null;

    ArrayList<Button> Botones = null;
    ArrayList<Button> BtnOperaciones = null;

    CalcLogic Calculadora = new CalcLogic();
    Character Operacion = '';



    Private View.OnClickListener numberOnClickListener =new View.OnClickListener(){
        @override
        public void onClick (View V) {
            setOperation ((Button)v).getText().toString();

        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetElements();

        btnx0.setOnClickListener( View.OnClickListener);
        btnx1.setOnClickListener();
        btnx2.setOnClickListener();
        btnx3.setOnClickListener();
        btnx4.setOnClickListener();
       btnx5.setOnClickListener();
        btnx6.setOnClickListener();
        btnx7.setOnClickListener();
       btnx8.setOnClickListener();
        btnx9.setOnClickListener();
        btnx10.setOnClickListener();
       btnx11.setOnClickListener();

    }


    private Void AnyadiraArray{
       Botones.add(btnx0) ;
        Botones.add(btnx1) ;
        Botones.add(btnx2);
        Botones.add(btnx3);
        Botones.add(btnx4);
        Botones.add(btnx5);
        Botones.add(btnx6);
        Botones.add(btnx7);
        Botones.add(btnx8);

        BtnOperaciones.add(btnx9);
        BtnOperaciones.add(btnx10);
        BtnOperaciones.add(btnx11);
    }

    private Void GetElements(){
        //Recojo los elementos Botones
        btnx0 = (Button) findViewById(R.id.btn0);
        btnx1 = (Button) findViewById(R.id.btn1);
        btnx2 = (Button) findViewById(R.id.btn2);
        btnx3 = (Button) findViewById(R.id.btn3);
        btnx4 = (Button) findViewById(R.id.btn4);
        btnx5 = (Button) findViewById(R.id.btn5);
        btnx6 = (Button) findViewById(R.id.btn6);
        btnx7 = (Button) findViewById(R.id.btn7);
        btnx8 = (Button) findViewById(R.id.btn8);
        btnx9 = (Button) findViewById(R.id.btn9);

        btnx10 = (Button) findViewById(R.id.btn10);
        btnx11 = (Button) findViewById(R.id.btn11);

        Datos = (TextView) findViewById(R.id.resultado);
    };


    private Void GetButtons {
        //Sera digual que getElements

        String[] numbers={"one","two","three"};
        String id;
                Button btn;
        for (int i=0; i<numbers.length;i++) {
        id="btn".concat(numbers[i]);
            btn=(Button) findViewById(getResources().getIdentifier(id,"id",getPackageName()));

        }


    }



    private Void PonEvento(){
        for (int i=0; i<Botones.size();i++){
            Botones.get(i).setOnClickListener(Number);
        }


        for (int i=0; i<BtnOperaciones.size();i++){
            BtnOperaciones.get(i).setOnClickListener();
        }
    };

    private void addEvent(Button Boton) {

        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Character X = Boton.getText().toString();
                switch (X) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':

                        //si es entre 0 y 9
                        Datos.setText(Datos.getText().toString() + X);
                        break;
                    case '=':
                        //Si es igual, operar , guardar resultado, y resetear operacion
                        Opera();
                        Operacion = '';
                        Datos.setText(Calculadora.getTotalString());


                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        if (Operacion = '') {
                            //Si es una operacion, guardar cual es, resetear texto

                            Calculadora.setTotal(Double.parseDouble(Datos.getText().toString()));

                            Datos.setText("");
                            Operacion = X;
                        } else {

                            Opera();
                            Datos.setText("");
                            Operacion = X;
                        }
                }
            }

        });
    }


    void Opera() {
        switch (Operacion) {
            case '+':
                Calculadora.add(Datos.getText().toString());
                break;
            case '-':
                Calculadora.subtract(Datos.getText().toString());
                break;
            case '*':
                Calculadora.multiply(Datos.getText().toString());
                break;
            case '/':
                Calculadora.divide(Datos.getText().toString());
                break;
        }
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
