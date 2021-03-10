package unipiloto.edu.co.vacunas.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import unipiloto.edu.co.vacunas.R;

public class LoginActivity extends AppCompatActivity {

    private Button registrar;
    private Button ingresar;
    private EditText email;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiComponents();
    }

    public void  intiComponents(){
        registrar = findViewById(R.id.registro);
        ingresar = findViewById(R.id.ingresar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
    }

    public void enviar(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}