package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
//import android.widget.Toolbar;

public class Productora1 extends AppCompatActivity {

    private Toolbar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productora1);
        //Referencia al toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);{
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //codificar cosas a ejecutar cuando le das tap a un tab
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //codificar cosas a ejecutar cuando un tab deja de estar seleccionado
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //codificar cosas a ejecutar cuando un tab se vuelve a seleccionar
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //incorporar el menu dentro de la activity
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.opcion)
            Solicitudes s = new Solicitudes();
            getSupportFragmentManager().beginTransaction().replace(R.id.)
        return super.onOptionsItemSelected(item);
    }