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
        setup();
    }

    public void  intiComponents(){
        registrar = findViewById(R.id.registro);
        ingresar = findViewById(R.id.ingresar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
    }

    public void setup(){
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println(email.getText());
                System.out.println(password.getText());
                enviar(v, email.getText().toString());
            }
        });
    }

    public void enviar(View v, String mensaje){
        Intent intent = new Intent(this, Prueba.class);
        intent.putExtra(Prueba.mensaje,mensaje);
        startActivity(intent);
    }
}