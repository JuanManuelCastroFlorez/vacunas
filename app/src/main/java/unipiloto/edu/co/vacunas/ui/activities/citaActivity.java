package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;
import unipiloto.edu.co.vacunas.ui.services.DelayedMessageService;

public class citaActivity extends Activity {

    public static String email="email";
    private String correo;
    private Button asignacion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashusucita);
        initComponents();
        Intent intent = getIntent();
        correo = intent.getStringExtra(email);
        System.out.println(correo);

    }

    public void initComponents(){
        this.asignacion = findViewById(R.id.button1);
    }

    public void setCita(){
        OkHttpClient client = new OkHttpClient();
        System.out.println(correo);
        String url = "https://colombiaprocesovacunas.herokuapp.com/asignarcita?correo=" + correo;
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
                    citaActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            alerta();
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

    public void alerta(){
        Toast.makeText(this, "Asignación realizada",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,getResources().getString(R.string.response));
        startService(intent);
    }

    public void asignar(View view) {
        setCita();
    }
}
