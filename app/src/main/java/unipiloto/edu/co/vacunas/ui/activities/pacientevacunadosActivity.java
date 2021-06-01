package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unipiloto.edu.co.vacunas.R;

public class pacientevacunadosActivity extends Activity {

    private String nombre = "usuario";
    private String docid = "id";
    private ArrayList<pacientevacunadosActivity.Vacunados> vacunados;

    private TextView tnombre ;
    private TextView tid ;

    private TableLayout tableLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashmpvt);
        initComponents();
        crearTabla();
        getPuntosV();
    }

    public void initComponents(){
        this.tableLayout = findViewById(R.id.tabla);
    }

    public void crearTabla(){
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));

        TextView textviewcorreo = new TextView(this);
        textviewcorreo.setText("Correo");
        textviewcorreo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewcorreo.setPadding(5,5,5,0);
        tableRow.addView(textviewcorreo);

        TextView textviewestado = new TextView(this);
        textviewestado.setText("Estado");
        textviewestado.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewestado.setPadding(5,5,5,0);
        tableRow.addView(textviewestado);

        TextView textviewsitio = new TextView(this);
        textviewsitio.setText("Sitio");
        textviewsitio.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewsitio.setPadding(5,5,5,0);
        tableRow.addView(textviewsitio);

        TextView textviewfecha = new TextView(this);
        textviewfecha.setText("Fecha");
        textviewfecha.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewfecha.setPadding(5,5,5,0);
        tableRow.addView(textviewfecha);

        TextView textviewvez = new TextView(this);
        textviewvez.setText("Vez_vacunado");
        textviewvez.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewvez.setPadding(5,5,5,0);
        tableRow.addView(textviewvez);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));

    }

    public void llenarTabla(){
        for (pacientevacunadosActivity.Vacunados vacunados : this.vacunados){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));

            TextView textviewcorreo = new TextView(this);
            textviewcorreo.setText(vacunados.getCorreo());
            textviewcorreo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewcorreo.setPadding(5,5,5,0);
            tableRow.addView(textviewcorreo);

            TextView textviewestado = new TextView(this);
            textviewestado.setText(vacunados.getEstado());
            textviewestado.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewestado.setPadding(5,5,5,0);
            tableRow.addView(textviewestado);

            TextView textviewsitio = new TextView(this);
            textviewsitio.setText(vacunados.getSitio());
            textviewsitio.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewsitio.setPadding(5,5,5,0);
            tableRow.addView(textviewsitio);

            TextView textviewfecha = new TextView(this);
            textviewfecha.setText(vacunados.getFecha());
            textviewfecha.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewfecha.setPadding(5,5,5,0);
            tableRow.addView(textviewfecha);

            TextView textviewvez = new TextView(this);
            textviewvez.setText(vacunados.getVez_vacunado());
            textviewvez.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewvez.setPadding(5,5,5,0);
            tableRow.addView(textviewvez);

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
        }
    }

    public void getPuntosV(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/getvactotal";
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
                    pacientevacunadosActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(response);
                            setInfo(myResponse);
                            llenarTabla();
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

    public void setInfo(String vacunados){
        Gson usu=new Gson();
        final Type tipoListaEmpleados = new TypeToken<List<pacientevacunadosActivity.Vacunados>>(){}.getType();
        this.vacunados = usu.fromJson(vacunados, tipoListaEmpleados);
        System.out.println(this.vacunados.size());
    }

    private class Vacunados {

        private String correo;
        private String estado;
        private String sitio;
        private String fecha;
        private String vez_vacunado;

        public Vacunados(){

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

        public String getSitio() {
            return sitio;
        }

        public void setSitio(String sitio) {
            this.sitio = sitio;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getVez_vacunado() {
            return vez_vacunado;
        }

        public void setVez_vacunado(String vez_vacunado) {
            this.vez_vacunado = vez_vacunado;
        }
    }
}
