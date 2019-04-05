package org.example.vitrix;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {

    private final int SETTINGS_INT = 1;
    private ConstraintLayout mainlayout;
    private AnimationDrawable dynamicbackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final String first_name = findViewById(R.id.register_first_name).toString();
        final String last_name = findViewById(R.id.register_last_name).toString();
        final String username = findViewById(R.id.register_username).toString();
        final String email = findViewById(R.id.register_email).toString();
        final String password = findViewById(R.id.register_password).toString();
        final String phone = findViewById(R.id.register_pnumber).toString();
        final String title = findViewById(R.id.register_title).toString();
        final String institute = findViewById(R.id.register_institute).toString();
        final String address = findViewById(R.id.register_address).toString();


        Button submit = findViewById(R.id.register_submit);

        // Set up dynamic background
        mainlayout = findViewById(R.id.mainlayout);
        dynamicbackground = (AnimationDrawable) mainlayout.getBackground();
        dynamicbackground.setEnterFadeDuration(2000);
        dynamicbackground.setExitFadeDuration(2000);
        dynamicbackground.start();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Access Api
                boolean success = AccessRegister(first_name, last_name, institute, username ,password);

                if (success) {
                    Intent launchRegisterProfile = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivityForResult(launchRegisterProfile, SETTINGS_INT);
                }
            }
        });

    }

    private boolean AccessRegister(String first_name, String last_name, String clinic_name, String username, String password) {
        try {
            String request = "http://api.vitrix.io/account/login";
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");

            connection.setRequestProperty("first_name", first_name);
            connection.setRequestProperty("last_name", last_name);
            connection.setRequestProperty("clinic_name", clinic_name);
            connection.setRequestProperty("username", username);
            connection.setRequestProperty("password", password);

            connection.connect();
        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
