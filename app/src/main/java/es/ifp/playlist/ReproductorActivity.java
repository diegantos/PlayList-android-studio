package es.ifp.playlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ReproductorActivity extends AppCompatActivity {

    protected Button btn;
    protected ImageButton play;
    protected ImageButton pause;
    protected ImageButton stop;
    protected TextView label1;
    protected TextView label2;
    protected DataBaseSQL db;
    private Bundle extras;
    private String paquete1;
    private String paquete2;
    private String paquete3;
    private Intent pasarPantalla;
    private MediaPlayer mp;
    private int longitud = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        btn = (Button) findViewById(R.id.btn_reproductor);
        play = (ImageButton) findViewById(R.id.play_reproductor);
        pause = (ImageButton) findViewById(R.id.pause_reproductor);
        stop = (ImageButton) findViewById(R.id.stop_reprodutor);
        label1 = (TextView) findViewById(R.id.label1_reproductor);
        label2 = (TextView) findViewById(R.id.label2_reproductor);

        db = new DataBaseSQL(this);

        extras = getIntent().getExtras();
        if(extras != null){
            paquete1 = extras.getString("ID");
            paquete2 = extras.getString("TITLE");
            paquete3 = extras.getString("URL");
            label1.setText("Título: " + paquete2);
            label2.setText(paquete3);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla = new Intent(ReproductorActivity.this, StartActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(longitud > 0){
                    mp.seekTo(longitud);
                }else {
                    try {
                        //https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3
                        mp = new MediaPlayer();
                        Uri url = Uri.parse(paquete3);
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mp.setDataSource(ReproductorActivity.this, url);
                        mp.prepare();
                        mp.start();
                    } catch (IOException e) {
                        Toast.makeText(ReproductorActivity.this, "Imposible reproducir el audio", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                mp.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                longitud = mp.getCurrentPosition();
                mp.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                longitud = 0;
                mp.prepareAsync();
            }
        });

    }
}