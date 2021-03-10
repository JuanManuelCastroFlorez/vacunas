package unipiloto.edu.co.vacunas.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import unipiloto.edu.co.vacunas.R;

public  class Registro_Activity extends Activity {

    public static String mensaje = "hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        Intent intent = getIntent();
        String prueba = intent.getStringExtra(mensaje);
        TextView test = findViewById(R.id.mensaje);
        test.setText(prueba);
    }
}