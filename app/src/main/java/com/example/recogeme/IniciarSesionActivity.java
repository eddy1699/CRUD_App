package com.example.recogeme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.recogeme.request.ValidarRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class IniciarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);


        Button Ingresar = findViewById(R.id.btnIngValidar);

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validar();
            }
        });

    }

    private void validar() {
        final EditText usuario =findViewById(R.id.txtIngUsuario);
        final EditText  clave   =findViewById(R.id.txtIngClave);
        final String usu=usuario.getText().toString();
        String cla=clave.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Iniciando...");
        progressDialog.show();

        Response.Listener<String> respuesta=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonrespuesta= new JSONObject(response);
                    boolean ok =jsonrespuesta.getBoolean("success");
                    if (ok==true){
                        Intent intent=new Intent(IniciarSesionActivity.this,InicioActivity.class);
                        intent.putExtra("NomUsuario",usu);
                        finish();
                        progressDialog.dismiss();


                        startActivity(intent);
                    }else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(IniciarSesionActivity.this);
                        alerta.setMessage("Datos incorrectos").setNegativeButton("Reintentar",null).create().show();
                        progressDialog.dismiss();
                        usuario.setText("");
                        clave.setText("");
                    }

                }catch (JSONException e){
                    e.getMessage();

                }

            }
        };
        ValidarRequest r = new ValidarRequest(usu,cla,respuesta);
        RequestQueue cola= Volley.newRequestQueue(IniciarSesionActivity.this);
        cola.add(r);
    }
}
