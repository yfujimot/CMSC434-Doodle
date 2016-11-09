package com.thinkfuji.cmsc434_doodle;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.support.v4.graphics.ColorUtils;

public class PaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        final View colorPreview = (View) findViewById(R.id.colorPreview);

        final SeekBar hSeeker = (SeekBar) findViewById(R.id.hSeeker);
        final SeekBar sSeeker = (SeekBar) findViewById(R.id.sSeeker);
        final SeekBar lSeeker = (SeekBar) findViewById(R.id.lSeeker);
        final SeekBar brushSeeker = (SeekBar) findViewById(R.id.brushSeeker);
        final SeekBar opacitySeeker = (SeekBar) findViewById(R.id.opacitySeeker);

        // Note: Seekbar Step is always 1, and can't be changed.
        // Init ranges
        hSeeker.setBottom(0);
        hSeeker.setMax(359);

        brushSeeker.setMax(50);

        sSeeker.setBottom(0);
        sSeeker.setMax(100); // Up to 1

        lSeeker.setBottom(0);
        lSeeker.setMax(100); // Up to 1

        hSeeker.setProgress(180);
        sSeeker.setProgress(50);
        lSeeker.setProgress(50);

        /* Listeners */
        Button cancelButton = (Button) findViewById(R.id.paletteCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button saveButton = (Button) findViewById(R.id.paletteSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                float[] colorValues = new float[]{(float) hSeeker.getProgress(), (float) sSeeker.getProgress()/100.0f, (float) lSeeker.getProgress()/100.0f};
                int brushColor = ColorUtils.HSLToColor(colorValues);
                data.putExtra("brushColor", brushColor); // as int
                setResult(RESULT_OK, data);
                finish();
            }
        });

        hSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("H Seek", i + "");
                float[] colorValues = new float[]{(float)i, ((float) sSeeker.getProgress())/(float)(100.0), ((float) lSeeker.getProgress())/(float)100.0};
                colorPreview.setBackgroundColor(ColorUtils.HSLToColor(colorValues));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("S Seek", i + "");
                float[] colorValues = new float[]{((int) hSeeker.getProgress()), i/(float)100.0, ((int) lSeeker.getProgress())/(float)100.0};
                colorPreview.setBackgroundColor(ColorUtils.HSLToColor(colorValues));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("B Seek", i + "");
                float[] colorValues = new float[]{((int) hSeeker.getProgress()), ((int) sSeeker.getProgress())/(float)100.0, i/(float)100.0};
                colorPreview.setBackgroundColor(ColorUtils.HSLToColor(colorValues));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        opacitySeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                colorPreview.setAlpha((float)i/50.0f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




}
