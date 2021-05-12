package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
    private CheckBox comorbilidad;
    private CheckBox riesgo;
    private EditText profesion;

    public void enviarRegistro(String nombres, String primer_apellido, String segundo_apellido, int edad, String docid, String correo, String comorbilidad, String riesgo, String profesion, String tusuario, String contraseña) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/registro?nombres=" + nombres + "&primer_apellido=" + primer_apellido + "&segundo_apellido=" + segundo_apellido + "&edad=" + edad + "&docid=" + docid + "&correo=" + correo + "&comorbilidad=" + comorbilidad + "&riesgo=" + riesgo + "&profesion=" + profesion +"&tusuario=" + tusuario + "&contrasena=" + contraseña + "";

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
                    System.out.println(myResponse);
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
        setContentView(R.layout.activity_usuario);
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
        profesion = (EditText) findViewById(R.id.profesion);
        password = findViewById(R.id.password);

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
        String comorbilidad = (this.comorbilidad.isChecked())?true+"":false+"";
        String riesgo = (this.riesgo.isChecked())?true+"":false+"";
        String profesion = this.profesion.getText().toString();
        int edad = Integer.parseInt(this.edad.getText().toString());
        String password = this.password.getText().toString();
        String usuario = "usuario";
        String tusuario = "4";
        enviarRegistro(nombre, fapellido, sapellido, edad, id, correo, comorbilidad, riesgo, profesion, tusuario, password);
    }
}