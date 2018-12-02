package org.example.vitrix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.pnumber);

        final RadioGroup gender = findViewById(R.id.gender);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pId = 6789;
                String pName = name.getText().toString();
                String pEmail = email.getText().toString();
                String pPhone = phone.getText().toString();
                final String pGender = ((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();


                try {
                    writeToDB(pId, pName, pEmail, pPhone, pGender);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void writeToDB(int id, String name, String email, String phone, String gender) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String databaseURL = "jdbc:mysql://localhost:3306/vitrix_data?user=admin&password=toor";

        Connection conn = DriverManager.getConnection(databaseURL);

        String query = " insert into user_data (iduser_data, name, email, phone, gender)"
                + " values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt     (1, id);
        preparedStmt.setString  (2, name);
        preparedStmt.setString  (3, email);
        preparedStmt.setString  (4, phone);
        preparedStmt.setString  (5, gender);

        preparedStmt.execute();

        conn.close();
    }
}
