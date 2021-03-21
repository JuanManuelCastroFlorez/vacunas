package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import unipiloto.edu.co.vacunas.R;

public  class RegistroActivity extends Activity {

    public static String mensaje = "hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Intent intent = getIntent();

    }
}