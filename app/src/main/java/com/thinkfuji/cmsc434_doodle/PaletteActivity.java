package com.thinkfuji.cmsc434_doodle;

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
                finish();
            }
        });

        final View colorPreview = (View) findViewById(R.id.colorPreview);

        final SeekBar hSeeker = (SeekBar) findViewById(R.id.hSeeker);
        final SeekBar sSeeker = (SeekBar) findViewById(R.id.sSeeker);
        final SeekBar bSeeker = (SeekBar) findViewById(R.id.bSeeker);

        // Note: Seekbar Step is always 1, and can't be changed.
        // Init ranges
        hSeeker.setBottom(0);
        hSeeker.setMax(359);

        sSeeker.setBottom(0);
        sSeeker.setMax(100); // Up to 1

        bSeeker.setBottom(0);
        bSeeker.setMax(100); // Up to 1

        hSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("H Seek", i + "");
                float[] colorValues = new float[]{(float)i, ((float) sSeeker.getProgress())/(float)(100.0), ((float) bSeeker.getProgress())/(float)100.0};
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
                float[] colorValues = new float[]{((int) hSeeker.getProgress()), i/(float)100.0, ((int) bSeeker.getProgress())/(float)100.0};
                colorPreview.setBackgroundColor(ColorUtils.HSLToColor(colorValues));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
    }




}
