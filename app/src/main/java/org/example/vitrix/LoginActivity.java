package org.example.vitrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private final int SETTINGS_INT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final String email = findViewById(R.id.login_email).toString();
        final String password = findViewById(R.id.login_password).toString();

        Button submit = findViewById(R.id.login_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Access Api
                boolean success = AccessLogin(email, password);

                if (success) {
                    Intent launchRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivityForResult(launchRegister, SETTINGS_INT);
                }
            }
        });
    }

    private boolean AccessLogin(String email, String password) {
        try {
            String request = "http://api.vitrix.io/auth/login/";
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");

            connection.setRequestProperty("username", email);
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
