package com.arnab.photofilter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class PhotoFrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_frame);

        ImageView layer = findViewById(R.id.layer);
        Drawable[] layers = new Drawable[2];
        layers[0] = getResources().getDrawable(R.drawable.toshiba);
        layers[1] = getResources().getDrawable(R.drawable.photo_frame);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layer.setImageDrawable(layerDrawable);

    }
}