package com.arnab.photofilter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void mirrorImage(View view) {
        startActivity(new Intent(this, MirrorImageActivity.class));
    }

    public void cameraFilm(View view) {
        startActivity(new Intent(this, CameraFilmImageActivity.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void photoFrame(View view) {
        startActivity(new Intent(this, PhotoFrameActivity.class));
    }

    public void palette(View view) {
        startActivity(new Intent(this, PaletteActivity.class));
    }
}