package com.example.adivinarnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spnOpciones;
    Button btnJugar;
    TextView txtIncognito;
    public static int intentos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJugar = (Button)findViewById(R.id.btnJugar);
        txtIncognito = (TextView)findViewById(R.id.txtNumero);
        spnOpciones = (Spinner)findViewById(R.id.spnOpciones);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones, android.R.layout.simple_spinner_item);
        spnOpciones.setAdapter(adapter);

        btnJugar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnJugar:
                intentos = Integer.parseInt(spnOpciones.getSelectedItem().toString());
                Intent i =  new Intent(MainActivity.this,AdivinaActivity.class);
                startActivity(i);

                break;
        }
    }


}