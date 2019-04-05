package org.example.vitrix;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final int SETTINGS_INT = 1;
    private ConstraintLayout mainlayout;
    private AnimationDrawable dynamicbackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up dynamic background
        mainlayout = findViewById(R.id.mainlayout);
        dynamicbackground = (AnimationDrawable) mainlayout.getBackground();
        dynamicbackground.setEnterFadeDuration(2000);
        dynamicbackground.setExitFadeDuration(2000);
        dynamicbackground.start();

        Button register = findViewById(R.id.main_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(launchRegister, SETTINGS_INT);
            }
        });

        Button login = findViewById(R.id.main_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(launchLogin, SETTINGS_INT);
            }
        });

        // adding camera capture button
        Button capture = findViewById(R.id.captureImage);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent launchCamera = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(launchCamera, SETTINGS_INT);
            }
        });
    }
}