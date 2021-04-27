package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;

public  class UsuarioActivity extends Activity {

    private EditText id;
    private EditText nombre;
    private EditText fapellido;
    private EditText sapellido;
    private EditText edad;
    private EditText password;
    private EditText correo;
    private EditText comorbilidad;
    private EditText riesgo;
    private EditText profesion;
    private Button enviar;


    private String mensajeFinal = "";

    public void enviarRegistro(String nombres, String primer_apellido, String segundo_apellido, int edad, String docid, String correo, String comorbilidad, String riesgo, String profesion, String tusuario, String contraseña) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/registro/?nombres=" + nombres + "&primer_apellido=" + primer_apellido + "&segundo_apellido=" + segundo_apellido + "&edad=" + edad + "&docid=" + docid + "&correo=" + correo + "&comorbilidad=" + comorbilidad + "&riesgo=" + riesgo + "&profesion=" + profesion +"&tusuario=" + tusuario + "&contraseña=" + contraseña + "";
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
                    UsuarioActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (myResponse.toString().equals("1")) {
                                mostrarMensaje("¡REGISTRO EXITOSO!");
                            } else {
                                mostrarMensaje("¡ERROR DE REGISTRO");
                            }
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);
        intiComponents();
    }

    public void intiComponents() {
        id = findViewById(R.id.id);
        nombre = findViewById(R.id.nombre);
        fapellido = findViewById(R.id.fapellido);
        sapellido = findViewById(R.id.sapellido);
        edad = findViewById(R.id.edad);
        correo = findViewById(R.id.correo);
        comorbilidad = findViewById(R.id.comorbilidad);
        riesgo = findViewById(R.id.riesgo);
        profesion = findViewById(R.id.profesion);
        password = findViewById(R.id.Password);
        enviar = findViewById(R.id.enviar);

    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void registrar(View view) {
        String id = this.id.getText().toString();
        String nombre = this.nombre.getText().toString();
        String fapellido = this.fapellido.getText().toString();
        String sapellido = this.sapellido.getText().toString();
        String correo = this.correo.getText().toString();
        String comorbilidad = this.comorbilidad.getText().toString();
        String riesgo = this.riesgo.getText().toString();
        String profesion = this.profesion.getText().toString();
        int edad = Integer.parseInt(this.edad.getText().toString());
        String password = this.password.getText().toString();
        String medico = "usuario";
        String tusuario = "4";
        enviarRegistro(nombre, fapellido, sapellido, edad, id, correo, comorbilidad, riesgo, profesion, tusuario, password);
    }
}