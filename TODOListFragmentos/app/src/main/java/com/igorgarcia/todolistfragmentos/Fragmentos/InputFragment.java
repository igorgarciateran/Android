package com.igorgarcia.todolistfragmentos.Fragmentos;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.igorgarcia.todolistfragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    public interface  TODOItemListener{
        public void addTodo(String todo)   ;

    }


    private Activity target;
   private Button BotonAdd;
    private EditText Texto;

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            this.target=(TODOItemListener) activity;

        }catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString()+"must implement TODOItemListener interface";)

        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View layout= return inflater.inflate(R.layout.fragment_input, container, false);
        BotonAdd=(Button) layout.findViewById(R.id.BtnAdd);
        Texto=(EditText) layout.findViewById(R.id.texto);

        addEventListener();


        return layout;
    }


    private void    addEventListener(){
      BotonAdd.setOnClickListener( new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              String todo=Texto.getText().toString();
              //TODO: pasar datos
              target.addTodo();
          }
      });
    };



}
