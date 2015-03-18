package com.igorgarcia.todolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> todos;
    private ArrayAdapter<String> aa;


    Button btnAdd = null;
    ListView TodoListView = null;
    TextView TodoText = null;

private final String Todo_Key="todos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        todos = new ArrayList<String>();
        //le decimos al adapter
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todos);

        btnAdd = (Button) findViewById(R.id.TODOBtn);
        TodoListView = (ListView) findViewById(R.id.TODOList);
        TodoText = (TextView) findViewById(R.id.TODOText);

        TodoListView.setAdapter(aa);

        this.addEventListeners();


    }

    private void addEventListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = TodoText.getText().toString();

                todos.add(0, todo);
                aa.notifyDataSetChanged();

                TodoText.setText("");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle OutState) {
        OutState.putStringArrayList(Todo_Key, todos);

        super.onSaveInstanceState(OutState);
    }


    @Override
    public void onRestoreInstanceState(Bundle OutState) {

        super.onRestoreInstanceState(OutState);

        todos.addAll(OutState.getStringArrayList(Todo_Key));

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
