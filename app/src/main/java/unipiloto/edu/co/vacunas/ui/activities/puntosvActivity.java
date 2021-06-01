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

public class puntosvActivity extends Activity {

    private String nombre = "usuario";
    private String docid = "id";
    private ArrayList<Zona> zonas;

    private TextView tnombre ;
    private TextView tid ;

    private TableLayout tableLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashusupuntosv);
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

        TextView textviewid = new TextView(this);
        textviewid.setText("Codigo");
        textviewid.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewid.setPadding(5,5,5,0);
        tableRow.addView(textviewid);

        TextView textviewnobre = new TextView(this);
        textviewnobre.setText("Nombre");
        textviewnobre.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textviewnobre.setPadding(5,5,5,0);
        tableRow.addView(textviewnobre);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));

    }

    public void llenarTabla(){
        for (Zona zona : this.zonas){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));

            TextView textviewid = new TextView(this);
            textviewid.setText(zona.getCodigo());
            textviewid.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewid.setPadding(5,5,5,0);
            tableRow.addView(textviewid);

            TextView textviewnobre = new TextView(this);
            textviewnobre.setText(zona.getNombre());
            textviewnobre.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textviewnobre.setPadding(5,5,5,0);
            tableRow.addView(textviewnobre);

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
        }
    }

    public void getPuntosV(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://colombiaprocesovacunas.herokuapp.com/getzonas";
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
                    puntosvActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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

    public void setInfo(String zonas){
        Gson usu=new Gson();
        final Type tipoListaEmpleados = new TypeToken<List<Zona>>(){}.getType();
        this.zonas = usu.fromJson(zonas, tipoListaEmpleados);
        System.out.println(this.zonas.size());
    }

    private class Zona {

        private String codigo;
        private String nombre;
        private String cantidad_total;
        private String estado_capacidad;

        public Zona(){

        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCantidad_total() {
            return cantidad_total;
        }

        public void setCantidad_total(String cantidad_total) {
            this.cantidad_total = cantidad_total;
        }

        public String getEstado_capacidad() {
            return estado_capacidad;
        }

        public void setEstado_capacidad(String estado_capacidad) {
            this.estado_capacidad = estado_capacidad;
        }

        @Override
        public String toString() {
            return "Zona{" +
                    "codigo='" + codigo + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", cantidad_total='" + cantidad_total + '\'' +
                    ", estado_capacidad='" + estado_capacidad + '\'' +
                    '}';
        }
    }
}
