package com.example.adivinarnumero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Random;

public class AdivinaActivity extends AppCompatActivity implements View.OnClickListener {


    int intentos = MainActivity.intentos;
    TextView numIncognito;
    EditText numIngresado;
    Button   btnAdivinar,btnReiniciar;
    int numeroRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina);
        numIncognito = (TextView)findViewById(R.id.txtNumeroAdv);
        numIngresado = (EditText)findViewById(R.id.edtNumAdivinar);
        btnAdivinar = (Button)findViewById(R.id.btnAdivinar);
        btnReiniciar = (Button)findViewById(R.id.btnReiniciar);

        btnAdivinar.setOnClickListener(this);
        btnReiniciar.setOnClickListener(this);
        numeroRandom = numberRandom();


    }

    @Override
    public void onClick(View v) {
        int numero;
        AlertDialog.Builder message = new AlertDialog.Builder(AdivinaActivity.this);
        switch (v.getId()){
            case R.id.btnAdivinar:

                if(intentos > 0){
                    numero = Integer.parseInt(numIngresado.getText().toString());
                    if(numero <= 100){
                        adivinarNumero(numero);
                    }else {
                        message.setMessage("Ingresar un Número Valido").setCancelable(false).setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog titulo = message.create();
                        titulo.setTitle("ERROR!");
                        titulo.show();
                        numIngresado.setText("");
                    }

                }

                break;

            case R.id.btnReiniciar:
                finish();

                break;
        }
    }

    public void adivinarNumero(int n){
        AlertDialog.Builder message = new AlertDialog.Builder(AdivinaActivity.this);

        if(n == numeroRandom){
            message.setMessage("Felicidades haz adivinado el número correctamente!").setCancelable(false).setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog titulo = message.create();
            titulo.setTitle("Ganaste!");
            titulo.show();
            numIncognito.setText(String.valueOf(numeroRandom));
            numIngresado.setText("");
            btnReiniciar.setVisibility(View.VISIBLE);
            btnAdivinar.setVisibility(View.INVISIBLE);

        }else if (n > numeroRandom){
            intentos--;
            if(intentos == 0){
               perdiste();
            }else{
                message.setMessage("El numero es menor que "+n+". Te quedan "+intentos+" intentos más").setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog titulo = message.create();
                titulo.setTitle("Casi!");
                titulo.show();
                numIngresado.setText("");
            }


        }else  if(n < numeroRandom){
            intentos--;
            if(intentos == 0){
                perdiste();
            }else{
                message.setMessage("El numero es mayor que "+n+". Te quedan "+intentos+" intentos más").setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog titulo = message.create();
                titulo.setTitle("Casi!");
                titulo.show();
                numIngresado.setText("");
            }


        }
    }


    public int numberRandom(){
        Random rd = new Random();
        return   rd.nextInt(101);
    }

    public void perdiste(){

        AlertDialog.Builder message = new AlertDialog.Builder(AdivinaActivity.this);
        message.setMessage("Se te han acabado el número de intentos!").setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog titulo = message.create();
        titulo.setTitle("Perdiste!");
        titulo.show();
        numIncognito.setText(String.valueOf(numeroRandom));
        numIngresado.setText("");
        btnReiniciar.setVisibility(View.VISIBLE);
        btnAdivinar.setVisibility(View.INVISIBLE);

    }

}