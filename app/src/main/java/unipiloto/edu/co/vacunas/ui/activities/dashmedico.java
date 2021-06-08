package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;

public class dashmedico extends Activity {
    public static String email="email";
    private TextView nombre;
    private TextView docid;
    private Usuario usuario;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashmedico);
        intiComponents();
    }

    private void intiComponents() {
        Intent intent=getIntent();
        String correo=intent.getStringExtra(email);
        get_usuario(correo);
        nombre=findViewById(R.id.textView4);
        docid=findViewById(R.id.textView5);
    }

    public void enviarusuario(String us){
        Gson usu=new Gson();
        usuario = usu.fromJson(us, Usuario.class);
        nombre.setText(usuario.getNombres());
        docid.setText(usuario.getDocid());
    }

    public void get_usuario(String correo){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/getusuario?correo="+correo;
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
                    dashmedico.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            enviarusuario(myResponse.toString());
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

    public void srv(View view) {
        Uri.Builder builder= new Uri.Builder();
        builder.scheme("https").authority("www.google.com").appendPath("maps").appendPath("dir").appendPath("").appendQueryParameter("api", "1").appendQueryParameter("destination", 4.632339710 + "," + -74.065350);
        String url= builder.build().toString();
        Log.d("Directions", url);
        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    public void adp(View view) {
        Intent intent=new Intent(view.getContext(),asigdiaActivity.class);
        startActivity(intent);
    }
    public void pvt(View view) {
        Intent intent=new Intent(view.getContext(),pacientevacunadosActivity.class);
        startActivity(intent);
    }


    private class Usuario  implements Serializable {
        private String nombres;
        private String docid;

        public Usuario(String nombres,String docid) {
            this.nombres = nombres;
            this.docid = docid;
        }
        public Usuario(){

        }

        public String getNombres() {
            return nombres;
        }

        public String getDocid() {
            return docid;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        @Override
        public String toString() {
            return "Usuario{" +
                    "nombres='" + nombres + '\'' +
                    ", docid='" + docid + '\'' +
                    '}';
        }
    }
}
