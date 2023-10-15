package com.example.myapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String nombreUsuario; // Campo local para almacenar el nombre del usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Obtén el nombre del usuario al iniciar la actividad
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            obtenerNombreUsuario(currentUser.getUid());
        }
    }

    public void ingresar(View v) {
        EditText campo1 = this.findViewById(R.id.correoinicio);
        String correo = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.contrasenia);
        String contrasenia = campo2.getText().toString();

        mAuth.signInWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        guardarInicioSesionEnRealtimeDatabase(user.getUid(), correo);

                        if (nombreUsuario != null) {
                            // Si el nombre de usuario se obtuvo previamente, guárdalo en la base de datos.
                            databaseReference.child("usuarios").child(user.getUid()).child("nombre").setValue(nombreUsuario);
                        }

                        // Solo si el inicio de sesión es exitoso, redirige al usuario a la actividad 'Productora1'.
                        Intent i = new Intent(this, Productora1.class);
                        startActivity(i);
                    } else {
                        // Error en las credenciales o la autenticación falló.
                        Toast.makeText(this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void registro(View v) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    private void guardarInicioSesionEnRealtimeDatabase(String userId, String correo) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = databaseRef.child("inicio_sesion").child(userId);
        userRef.child("correo").setValue(correo);
    }

    private void obtenerNombreUsuario(String userId) {
        DatabaseReference userRef = databaseReference.child("usuarios").child(userId);
        userRef.child("nombre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    nombreUsuario = dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores si es necesario
            }
        });
    }
}


