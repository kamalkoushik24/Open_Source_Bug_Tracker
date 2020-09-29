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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LogActivity extends AppCompatActivity {
    TextInputEditText desc;
    Spinner spinner1;
    Spinner spinner2;
    EditText pDetails;
    int id;
//    TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        id = getIntent().getIntExtra("id", 0);

        FloatingActionButton delbtn;
        FloatingActionButton savebtn;
        FloatingActionButton archivebtn;
        LogDatabase logDatabase;

//        todo

        desc = findViewById(R.id.desc);

        delbtn = findViewById(R.id.log_delete);
        savebtn = findViewById(R.id.savebtn);
        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        pDetails = findViewById(R.id.pDetails);
        archivebtn = findViewById(R.id.archive);
        String desc1 = getIntent().getStringExtra("desc");
        desc.setText(desc1);
        String pdesc = getIntent().getStringExtra("pDetails");
        pDetails.setText((pdesc));


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Application, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        if (spinner1.getSelectedItem().toString().equals("App1")){

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.category1, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);

        }
        else if (spinner1.getSelectedItem().toString().equals("App2")){
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                    R.array.category3, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter3);
        }
        else if (spinner1.getSelectedItem().toString().equals("App3")){
            ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                    R.array.category3, android.R.layout.simple_spinner_item);
            adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter4);
        }



        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logDatabase.logDao().delete(id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log Deleted", Toast.LENGTH_SHORT).show();


            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logDatabase.logDao().save(desc.getText().toString(),
                        spinner1.getSelectedItem().toString(),
                        spinner2.getSelectedItem().toString(),
                        pDetails.getText().toString(), id);
                startActivity(intent);
            }
        });
        archivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.setEnabled(false);
                spinner1.setEnabled(false);
                spinner2.setEnabled(false);
                pDetails.setEnabled(false);
            }
        });

        }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.logDatabase.logDao().save(desc.getText().toString(),
                spinner1.getSelectedItem().toString(),
                spinner2.getSelectedItem().toString(),
                pDetails.getText().toString(), id);
    }
}