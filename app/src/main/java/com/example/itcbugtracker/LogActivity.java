package com.example.itcbugtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        final int id = getIntent().getIntExtra("id", 0);
        TextInputEditText desc;
        Spinner spinner1;
        Spinner spinner2;
        FloatingActionButton delbtn;
        LogDatabase logDatabase;

//        todo

        delbtn = findViewById(R.id.log_delete);
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logDatabase.logDao().delete(id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log Deleted", Toast.LENGTH_SHORT).show();


            }
        });
        }




    }