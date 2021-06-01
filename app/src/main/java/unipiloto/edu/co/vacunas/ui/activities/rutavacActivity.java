package unipiloto.edu.co.vacunas.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import unipiloto.edu.co.vacunas.R;

public class rutavacActivity extends Activity {

    private String nombre = "usuario";
    private String docid = "id";

    private TextView tnombre ;
    private TextView tid ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashmrutavac);
        initComponents();
        Intent intent = getIntent();
        tnombre.setText(intent.getStringExtra(nombre));
        tid.setText(intent.getStringExtra(docid));
    }

    public void initComponents(){
        this.tnombre = findViewById(R.id.textView4);
        this.tid = findViewById(R.id.textView5);
    }
}
