package unipiloto.edu.co.vacunas.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;
import unipiloto.edu.co.vacunas.ui.entidades.Receptor;
import unipiloto.edu.co.vacunas.ui.entidades.Representante;
import unipiloto.edu.co.vacunas.ui.entidades.Usuario;

public class LoginActivity extends AppCompatActivity {

    private Button registrar;
    private Button ingresar;
    private Spinner tusuario;
    private EditText email;
    private EditText password;


    String mensajeFinal = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiComponents();
    }

    public void  intiComponents(){
        registrar = findViewById(R.id.registro);
        ingresar = findViewById(R.id.ingresar);
        tusuario = findViewById(R.id.spinner);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
    }



    public void f_registrar(View view) {
    Intent intent;
        switch (tusuario.getSelectedItem().toString()){
            case "1":intent = new Intent(this, MedicoActivity.class);
                    startActivity(intent);
                    break;
            case "2":intent = new Intent(this, ReceptorActivity.class);
                startActivity(intent);
                break;
            case "3":intent = new Intent(this, RepresentanteActivity.class);
                startActivity(intent);
                break;
            case "4":intent = new Intent(this, UsuarioActivity.class);
                startActivity(intent);
                break;




        }
    }

    public void comprobar_credenciales(String correo, String contraseña, String tusuario){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/login/?correo="+correo+"&contrasena="+contraseña+"&tusuario="+tusuario;
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
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                          if (myResponse.toString().equals("1")){
                              mensajeFinal = "credenciales correctas";
                             dash();
                          }
                          else{
                              mostrarErrorCredenciales();
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
    public void dash(){
        Intent intent;
        switch (tusuario.getSelectedItem().toString()){
            case "1":intent = new Intent(this, dashmedico.class);
                startActivity(intent);
                break;
            case "2":intent = new Intent(this, dashreceptor.class);
                startActivity(intent);
                break;
            case "3":intent = new Intent(this, dashrepresentante.class);
                startActivity(intent);
                break;
            case "4":intent = new Intent(this, dashusuario.class);
                intent.putExtra(dashusuario.email,email.getText().toString());
                startActivity(intent);
                break;




        }
    }

    public void logearse(View view) {
        String temail = email.getText().toString();
        String tpassword =  password.getText().toString();
        String usuario = tusuario.getSelectedItem().toString();
        comprobar_credenciales(temail,tpassword,usuario);

    }

    public void iniciarDashboard(String mensaje){
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(DashboardActivity.EXTRA_MESSAGE,mensaje);
        startActivity(intent);
    }

    public void mostrarErrorCredenciales(){
        Toast.makeText(this, "Credenciales Incorrectas",Toast.LENGTH_SHORT).show();
    }
}