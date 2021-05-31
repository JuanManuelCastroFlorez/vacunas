package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
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
import unipiloto.edu.co.vacunas.ui.entidades.Receptor;
import unipiloto.edu.co.vacunas.ui.logic.GMailSender;

public class ReceptorActivity extends Activity {

    private EditText id;
    private EditText nombre;
    private EditText fapellido;
    private EditText sapellido;
    private EditText edad;
    private EditText correo;
    private EditText password;
    private EditText localidad;
    private EditText profesion;
    private EditText canti_vacunas;
    private Button enviar;


    private String mensajeFinal = "";

    public void enviarRegistro(String nombres, String primer_apellido, String segundo_apellido,int edad, String docid,String correo,String profesion,String puesto_asignacion,String localidad, int cant_vacunas,String entidad,String tusuario, String contraseña ){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/registro?nombres="+nombres+"&primer_apellido="+primer_apellido+"&segundo_apellido="+segundo_apellido+"&edad="+edad+"&docid="+docid+"&correo="+correo+"&profesion="+profesion+"&puesto_asignacion="+puesto_asignacion+"&localidad="+localidad+"&cant_vacunas="+cant_vacunas+"&entidad&tusuario="+tusuario+"&contrasena="+contraseña+"";
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
                    ReceptorActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (myResponse.toString().equals("1")){
                                mostrarMensaje("¡REGISTRO EXITOSO!");
                                GMailSender em = new GMailSender("softdes08@gmail.com", "0aBuwaGJ");
                                try {
                                    em.sendMail("Confirmacion de registro","Su usuario es: " + correo + " su contraseña es: " + contraseña,"123123123123123", "manuelcastrog9@gmail.com");
                                    return;
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else{
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
        setContentView(R.layout.activity_receptor);
        intiComponents();
    }

    public void  intiComponents(){
        id = findViewById(R.id.id);
        nombre = findViewById(R.id.nombre);
        fapellido = findViewById(R.id.fapellido);
        sapellido = findViewById(R.id.sapellido);
        edad = findViewById(R.id.edad);
        correo = findViewById(R.id.correo);
        password = findViewById(R.id.Password);
        localidad = findViewById(R.id.localidad);
        profesion = findViewById(R.id.profesion);
        canti_vacunas = findViewById(R.id.canti_vacunas);
        enviar = findViewById(R.id.enviar);

    }

    public void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show();
    }
    public void registrar(View view){
        String id  = this.id.getText().toString();
        String nombre = this.nombre.getText().toString();
        String fapellido = this.fapellido.getText().toString();
        String sapellido = this.sapellido.getText().toString();
        String correo = this.correo.getText().toString();
        int edad = Integer.parseInt(this.edad.getText().toString());
        String password = this.password.getText().toString();
        String profesion= this.profesion.getText().toString();
        String localidad = this.localidad.getText().toString();
        int canti_vacunas= Integer.parseInt(this.canti_vacunas.getText().toString());
        String tusuario = "2";
        enviarRegistro(nombre,fapellido,sapellido,edad,id,correo,profesion,"",localidad,canti_vacunas,"",tusuario,password);
    }
}
