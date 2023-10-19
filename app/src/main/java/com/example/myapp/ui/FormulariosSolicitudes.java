package com.example.myapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.ui.Formulario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormulariosSolicitudes extends AppCompatActivity {
    private Spinner spinner;
    private EditText nombreEditText, telefonoEditText, mensajeEditText;
    private Button enviarButton;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularios_solicitudes);

        spinner = findViewById(R.id.spinner);
        nombreEditText = findViewById(R.id.nombreEditText);
        telefonoEditText = findViewById(R.id.telefonoEditText);
        mensajeEditText = findViewById(R.id.mensajeEditText);
        enviarButton = findViewById(R.id.enviarButton);

        // Configura el Spinner con las opciones de las productoras
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ProductorasOpciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Inicializa la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("formularios");
        firebaseAuth = FirebaseAuth.getInstance();

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el usuario actual
                // Obtiene el usuario actual
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // El usuario no ha iniciado sesión, maneja esto como prefieras
                    return;
                }

                // Obtiene los datos del formulario
                String productora = spinner.getSelectedItem().toString();
                String nombre = nombreEditText.getText().toString();
                String telefono = telefonoEditText.getText().toString();
                String mensaje = mensajeEditText.getText().toString();
                String correo = user.getEmail(); // Obtiene el correo del usuario actual
                String userIdentifier = user.getUid(); // Utiliza el UID del usuario como identificador único


                // Crea un objeto Formulario con los datos
                Formulario formulario = new Formulario(productora, nombre, correo, telefono, mensaje);

                // Crea una carpeta específica para el usuario en la Realtime Database
                String key = databaseReference.child(userIdentifier).push().getKey();
                databaseReference.child(userIdentifier).child(key).setValue(formulario);


                // Limpia los campos del formulario
                nombreEditText.setText("");
                telefonoEditText.setText("");
                mensajeEditText.setText("");

                // Muestra un mensaje de confirmación al usuario
                Toast.makeText(FormulariosSolicitudes.this, "Formulario enviado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}