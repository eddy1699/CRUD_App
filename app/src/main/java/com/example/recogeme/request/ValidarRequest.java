package com.example.recogeme.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class ValidarRequest extends StringRequest {


    private static  final  String ruta = "";
    private Map<String, String> parametros;

    public ValidarRequest(String ingUsuario, String ingClave ,Response.Listener<String>listener){
        super(Request.Method.POST,ruta,listener,null);
        parametros= new HashMap<>();
        parametros.put("Correo",ingUsuario+"");
        parametros.put("Clave",ingClave+"");

    }
    public Map<String, String> getParams() {
        return parametros;
    }
}
