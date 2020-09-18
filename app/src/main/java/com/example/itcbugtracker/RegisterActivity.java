package com.example.itcbugtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.itcbugtracker.R.drawable.ic_baseline_error_24;
import static com.example.itcbugtracker.R.drawable.ic_baseline_warning_24;

public class RegisterActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText newusr = findViewById(R.id.editTextTextPersonName);
        final EditText pass = findViewById(R.id.editTextTextPassword);
        final EditText repass = findViewById(R.id.editTextTextPassword3);
        Button regbutton = findViewById(R.id.reg);
        Button redirect = findViewById(R.id.logredirect);
        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usrname = newusr.getText().toString();
                final String passwrd = pass.getText().toString();
                final String repasswrd = repass.getText().toString();

                if (usrname.isEmpty() || passwrd.isEmpty() || repasswrd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill in all the details", Toast.LENGTH_SHORT).show();
                } else if (!repasswrd.equals(passwrd)) {
                    Drawable icon =
                            getResources().getDrawable(ic_baseline_warning_24);
                    if (icon != null) {
                        icon.setBounds(0, 0,
                                icon.getIntrinsicWidth(),
                                icon.getIntrinsicHeight());
                    }
                    repass.setError("Passwords Do Not Match!!", icon);
                    pass.setError("Passwords Do Not Match!!", icon);

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao Dao = userDatabase.userDao();

                    User user = Dao.checkuser(usrname);
                    if (user != null) {
                        newusr.requestFocus();
                        newusr.setError("Username Exists!");


                    }
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao Dao = userDatabase.userDao();

                    User user = Dao.checkuser(usrname);
                    if (user != null) {
                        newusr.requestFocus();
                        newusr.setError("Username Exists!");


                    } else {
                        User user1 = new User();
                        user1.setPasswd(passwrd);
                        user1.setUsrname(usrname);
                        Dao.register(user1);
                        Toast.makeText(getApplicationContext(), "Registration Succesfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                    }

                }
            }
        });
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}




