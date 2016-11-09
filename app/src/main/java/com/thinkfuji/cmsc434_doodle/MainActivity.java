package com.thinkfuji.cmsc434_doodle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.security.Permission;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int REQUEST_PALETTE = 99;
        final int REQUEST_WRITE_PERMISISON = 999;
        final Canvas canvas = (Canvas) findViewById(R.id.canvas);

        canvas.setDrawingCacheEnabled(true);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvas.clearCanvas();
            }
        });

        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PaletteActivity.class);
                startActivityForResult(intent, REQUEST_PALETTE);
            }
        });

        Button captureButton = (Button) findViewById(R.id.captureButton);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = canvas.getDrawingCache();
                try {
//                    b.compress(Bitmap.CompressFormat.JPEG, 95, new FileOutputStream(Environment.getDataDirectory() + "/saved.jpg"));
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                            // Show an expanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                        } else {

                            // No explanation needed, we can request the permission.

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_WRITE_PERMISISON);
                        }
                    } else {
                        MediaStore.Images.Media.insertImage(getContentResolver(), image, "Doodler Screenshot" , "Masterpiece.");
                        Toast.makeText(MainActivity.this, "Screenshot saved to gallery.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.i("Capture", e.getLocalizedMessage().toString());
                }
            }
        });

        Button undoButton = (Button) findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                canvas.getPaths().rewind();
            }
        });

        Button redoButton = (Button) findViewById(R.id.redoButton);
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void writeImageToDisk() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Canvas canvas = (Canvas) findViewById(R.id.canvas);
        if (resultCode == RESULT_OK) {
            int brushColor = data.getIntExtra("brushColor", 0x0);
            canvas.setBrushColor(brushColor);
        } else {
            Log.i("Palette Result", "User canceled");
        }

    }
}
