package es.ifp.playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearActivity extends AppCompatActivity {

    protected EditText label1;
    protected EditText label2;
    protected Button btn;
    protected String texto1;
    protected String texto2;
    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        label1 = (EditText) findViewById(R.id.label1_crear);
        label2 = (EditText) findViewById(R.id.label2_crear);
        btn = (Button) findViewById(R.id.btn1_crear);

        db = new DataBaseSQL(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto1 = label1.getText().toString();
                texto2 = label2.getText().toString();
                if( texto1.equals("") || texto2.equals("")){
                    Toast.makeText(CrearActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else {
                    db.insertAudio(texto1,texto2);
                    Toast.makeText(CrearActivity.this, "Audio guardado correctamente", Toast.LENGTH_SHORT).show();
                    Intent pasarPantalla = new Intent(CrearActivity.this, StartActivity.class);
                    pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(pasarPantalla);
                }
            }
        });

    }
}