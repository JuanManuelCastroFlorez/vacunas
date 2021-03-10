package unipiloto.edu.co.vacunas.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import unipiloto.edu.co.vacunas.R;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button registrar;
    Button ingresar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();

    }



    public void setup(){
        registrar = findViewById(R.id.registro);
        ingresar = findViewById(R.id.ingresar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);

        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email.getText();

                enviar(email.getText().toString());
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void enviar(String mensaje){
        Intent  intent = new Intent(this, Prueba.class);
        intent.putExtra(Prueba.mensaje,mensaje);
        System.out.println("si entraaaaaa");
    }


}