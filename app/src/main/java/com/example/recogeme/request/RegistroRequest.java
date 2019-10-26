package com.example.recogeme.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {

    private static  final  String ruta = "";
    private Map<String, String> parametros;

    public RegistroRequest(String RegNom, String RegApe, Date RegFecNac, String Email, String Clave, Response.Listener<String>listener){
        super(Method.POST,ruta,listener,null);
        parametros= new HashMap<>();
        parametros.put("Nombre",RegNom+"");
        parametros.put("Apellido",RegApe+"");
        parametros.put("FechaNacimiento",RegFecNac+"");
        parametros.put("Correo",Email+"");
        parametros.put("Clave",Clave+"");
    }
    public Map<String, String> getParams() {
        return parametros;
    }

}
