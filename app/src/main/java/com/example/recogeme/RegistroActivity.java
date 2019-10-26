package com.example.recogeme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.recogeme.request.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrar();


    }

    private void registrar() {

        final EditText etnombre=findViewById(R.id.txtRegNom);
        final EditText etapellido=findViewById(R.id.txtRegApe);
        final EditText etdia    =findViewById(R.id.txtRegDia);
        final EditText etmes =findViewById(R.id.txtRegMes);
        final EditText etanio   =findViewById(R.id.txtRegAnio);
        final EditText etmail   =findViewById(R.id.txtRegEmail);
        final EditText etclave   =findViewById(R.id.txtRegClave);
        final EditText etclaveconf   =findViewById(R.id.txtRegCalveConfir);

        Button Registrar = findViewById(R.id.btnRegRegistrarse);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre,apellido,email,clave,claveconf,fec;
                int dia,mes,anio;
                Date nacfec;

                nombre=etnombre.getText().toString();
                apellido=etapellido.getText().toString();
                email=etmail.getText().toString();
                claveconf=etclaveconf.getText().toString();
                clave=etclave.getText().toString();
                dia=Integer.parseInt((etdia.getText().toString()));
                mes=Integer.parseInt((etmes.getText().toString()));
                anio=Integer.parseInt((etanio.getText().toString()));

                fec= dia+"/"+mes+"/"+anio;

                nacfec= Date.valueOf(fec);




                if (clave == email || clave == nombre || clave == apellido){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(RegistroActivity.this);
                    alerta.setMessage("La contraseña no puede ser igual a los datos del formulario").setNegativeButton("Reintentar",null).create().show();

                }
                else if(clave == claveconf){
                    Response.Listener<String> respuesta = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonres= new JSONObject(response);
                                boolean ok=jsonres.getBoolean("success");
                                if (ok==true){
                                    Intent i = new Intent(RegistroActivity.this,IniciarSesionActivity.class);
                                    RegistroActivity.this.startActivity(i);
                                    RegistroActivity.this.finish();
                                }else {
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(RegistroActivity.this);
                                    alerta.setMessage("Fallo Registro").setNegativeButton("Reintentar",null).create().show();
                                }

                            }catch (JSONException e){ e.getMessage();}

                        }
                    };

                    RegistroRequest r = new RegistroRequest(nombre,apellido,nacfec,email,clave,respuesta);
                    RequestQueue cola= Volley.newRequestQueue(RegistroActivity.this);
                    cola.add(r);

                }








            }
        });
    }
}
