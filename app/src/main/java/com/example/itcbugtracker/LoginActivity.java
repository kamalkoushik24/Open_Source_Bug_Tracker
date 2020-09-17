package com.example.itcbugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    public Boolean login = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.editTextNumberPassword);
        Button buttonLogin = findViewById(R.id.button);
        db = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter all details", Toast.LENGTH_SHORT);
                }
                else{
                    Boolean check = db.checkPass(user, pass);
                    if(check == true){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }


}