package edu.kpi.android.labs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;

public class AudioActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private EditText audioLinkInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        Toolbar toolbar = findViewById(R.id.audio_toolbar);
        setSupportActionBar(toolbar);

        audioLinkInput = findViewById(R.id.audio_link);

        mediaPlayer = MediaPlayer.create(this, R.raw.sample);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop(null);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void play(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
    }

    public void stop(View view) {
        mediaPlayer.stop();

        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAudio(View view) {
        this.stop(view);
        try {
            mediaPlayer.setDataSource(audioLinkInput.getText().toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            stop(null);
        }
    }
}