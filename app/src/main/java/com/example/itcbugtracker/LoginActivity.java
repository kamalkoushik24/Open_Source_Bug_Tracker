package com.example.itcbugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public Boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.editTextNumberPassword);
        final Button login = findViewById(R.id.button);
        final Button regbutton = findViewById(R.id.regbuttn);
        username.setText("");
        password.setText("");
        ImageView imageView = findViewById(R.id.imageView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usrname = username.getText().toString();
                final String passwrd = password.getText().toString();
                if (usrname.isEmpty() || passwrd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill in all the details", Toast.LENGTH_SHORT).show();
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao Dao = userDatabase.userDao();

                    User user = Dao.login(usrname, passwrd);
                    if (user == null) {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }


                }
            }
        });
        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}