package org.example.vitrix;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Parameter;
import java.security.Policy;

public class CameraActivity extends AppCompatActivity {

    private final int SETTINGS_INT = 1;
    Button startSearching;
    ImageView screeningPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        startSearching = (Button) findViewById(R.id.StartScreeningButton);
        startSearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Uri fileUri = getOutputMediaFileUri(MediaStore.MEDIA_TYPE_IMAGE);
                // camera.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                try {
                    CameraManager cameraManager = (CameraManager) getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        for (String id : cameraManager.getCameraIdList()) {

                            // Turn on the flash if camera has one
                            if (cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    cameraManager.setTorchMode(id, true);
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    Toast.makeText(getApplicationContext(), "Torch Failed: " + e2.getMessage(), Toast.LENGTH_SHORT).show();
                }

                startActivityForResult(camera, 1337);
            }

        });
    }

    @Override
    protected void onActivityResult (int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        Bitmap screening = (Bitmap) data.getExtras().get("data");
        screeningPicture = (ImageView) findViewById(R.id.ScreeningPicture);
        screeningPicture.setImageBitmap(screening);
    }

}
