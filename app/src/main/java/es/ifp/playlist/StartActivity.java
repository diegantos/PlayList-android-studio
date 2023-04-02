package es.ifp.playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    protected ListView lista;
    protected DataBaseSQL db;
    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;
    private String contenidoItem = "";
    private String[] partes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lista_start);

        db = new DataBaseSQL(this);

        filas = db.getAllAudios();
        adaptador = new ArrayAdapter<String>(StartActivity.this, android.R.layout.simple_list_item_1, filas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contenidoItem = adapterView.getItemAtPosition(i).toString();
                partes = contenidoItem.split(".-");
                Audio audio = db.getAudio(Integer.parseInt(partes[0]));
                if(audio != null){

                }

                Intent pasarPantalla = new Intent(StartActivity.this, ReproductorActivity.class);
                pasarPantalla.putExtra("ID", Integer.toString(audio.getId()));
                pasarPantalla.putExtra("TITLE", audio.getTitle());
                pasarPantalla.putExtra("URL", audio.getUrl());
                finish();
                startActivity(pasarPantalla);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_add_start:
                Intent pasarPantalla = new Intent(StartActivity.this, CrearActivity.class);
                finish();
                startActivity(pasarPantalla);
                return true;
            case R.id.item_salir_start:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}