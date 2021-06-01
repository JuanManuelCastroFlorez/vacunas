package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;

public class estadoActivity extends Activity {


    public static String email="email";
    private String correo;

    private TextView estado;
    private TextView fecha;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashusuestado);
        initComponents();
        Intent intent = getIntent();
        correo = intent.getStringExtra(email);
        getCita();
    }

    public void getCita(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/getSolicitud?correo=" + correo;
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    estadoActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setInfo(myResponse);
                        }
                    });
                }
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        });
    }

    public void setInfo(String sol){
        Gson usu=new Gson();
        Solicitud solicitud = usu.fromJson(sol, Solicitud.class);
        estado.setText((solicitud.getEstado().equals("true")?"Activo":"Pendiente"));
        fecha.setText(solicitud.getFecha());
    }

    public void initComponents(){
        this.estado = findViewById(R.id.textView13);
        this.fecha = findViewById(R.id.textView14);
    }

    private class Solicitud{

        private String correo;
        private String estado;
        private String fecha;

        public Solicitud(){

        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        @Override
        public String toString() {
            return "Solicitud{" +
                    "correo='" + correo + '\'' +
                    ", estado='" + estado + '\'' +
                    ", fecha='" + fecha + '\'' +
                    '}';
        }
    }

}
