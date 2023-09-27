package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void registro2(View v){
        EditText campo1 = this.findViewById(R.id.nombre2);
        String nombre2 = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.correo);
        String correo = campo2.getText().toString();
        EditText campo3 = this.findViewById(R.id.contrasenia2);
        String contrasenia2 = campo3.getText().toString();
        EditText campo4 = this.findViewById(R.id.contrasenia3);
        String contrasenia3 = campo4.getText().toString();

        if (isValidRegistration(nombre2, correo, contrasenia2, contrasenia3)){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    private boolean isValidRegistration(String nombre2, String correo, String contrasenia2, String contrasenia3){
        if(nombre2.equals("camila") && correo.equals("camila@gmail.com") && contrasenia2.equals("123") && contrasenia3.equals("123")){
            return true;
        }
        return false;
    }
}