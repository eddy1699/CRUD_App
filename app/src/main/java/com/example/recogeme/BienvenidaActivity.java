package com.example.recogeme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BienvenidaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        Button btnRegistro = findViewById(R.id.btnResgitrofrm);
        Button btnIncioSesion = findViewById(R.id.btnIngresarfrm);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BienvenidaActivity.this,RegistroActivity.class);
                startActivity(i);
            }
        });

        btnIncioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BienvenidaActivity.this,IniciarSesionActivity.class);
                startActivity(i);
            }
        });




    }
}
