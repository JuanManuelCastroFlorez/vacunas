package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;

public class faseActivity extends Activity {

    public static String email="email";
    private String correo;
    private TextView fase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashusufase);
        initComponents();
        Intent intent = getIntent();
        correo = intent.getStringExtra(email);
        fase();
    }

    public void initComponents(){
        this.fase = findViewById(R.id.textView15);
    }

    public void setFase(String fase){
        this.fase.setText(fase);
    }


    public void fase(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/consulta_fase?correo=" + correo;
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
                    faseActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setFase(myResponse);
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

}
