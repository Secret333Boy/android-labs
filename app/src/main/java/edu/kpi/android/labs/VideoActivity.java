package edu.kpi.android.labs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class VideoActivity extends AppCompatActivity {
    private VideoView videoPlayer;
    private EditText videoLinkInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Toolbar toolbar = findViewById(R.id.video_toolbar);
        setSupportActionBar(toolbar);

        videoPlayer = findViewById(R.id.videoPlayer);
        videoLinkInput = findViewById(R.id.video_link);

        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.lab);
        videoPlayer.setVideoURI(myVideoUri);
//        videoPlayer.setVideoPath("/storage/emulated/0/Movies/VID_20230518_080816.mp4");

        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
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
        videoPlayer.start();
    }

    public void pause(View view) {
        videoPlayer.pause();
    }

    public void stop(View view) {
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }

    public void loadVideo(View view) {
        this.stop(view);
        videoPlayer.setVideoPath(videoLinkInput.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stop(null);
    }
}