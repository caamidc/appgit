package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ingresar(View v){
        EditText campo1 = this.findViewById(R.id.nombre);
        String nombre = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.contrasenia);
        String contrasenia = campo2.getText().toString();

        if(nombre.equals("camila") && contrasenia.equals("123")){
            Intent i = new Intent(this, Productora1.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
        }
    }

    public void registro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}