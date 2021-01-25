package com.arnab.photofilter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MirrorImageActivity extends AppCompatActivity {

    private ImageView mirror_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirror_image);

        mirror_image = findViewById(R.id.mirror_image);
        Drawable drawableToshiba = getResources().getDrawable(R.drawable.toshiba);
        Bitmap bitmapToshiba = ((BitmapDrawable) drawableToshiba).getBitmap();
        Bitmap convertedBitmap = mirrorImage(bitmapToshiba);
        mirror_image.setImageBitmap(convertedBitmap);
    }

    public static Bitmap mirrorImage(Bitmap original) {
        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = original.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                finalImage.setPixel(width - x - 1, y, Color.argb(A, R, G, B));
            }
        }
        return finalImage;
    }

}