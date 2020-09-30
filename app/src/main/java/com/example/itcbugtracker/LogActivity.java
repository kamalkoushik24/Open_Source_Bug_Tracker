package com.example.itcbugtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        FloatingActionButton delbtn;
        FloatingActionButton savebtn;
        FloatingActionButton archivebtn;
        final TextInputEditText desc;
        final EditText pDetails;
        final Spinner spinner1;
        final Spinner spinner2;
        spinner1 = findViewById(R.id.app);
        spinner2 = findViewById(R.id.cat);
        desc = findViewById(R.id.desc);
        pDetails = findViewById(R.id.pDetails);
        LogDatabase logDatabase;


        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        final int id = getIntent().getIntExtra("id", 0);


        new Thread(new Runnable() {
            @Override
            public void run() {
                int pos1 = getIntent().getIntExtra("pos1", 0);
                int pos2 = getIntent().getIntExtra("pos2", 0);
                String description = getIntent().getStringExtra("desc");
                String details = getIntent().getStringExtra("pDetails");
                TextInputEditText desc;
                EditText pDetails;
                Spinner spinner1;
                Spinner spinner2;
                spinner1 = findViewById(R.id.app);
                spinner2 = findViewById(R.id.cat);
                desc = findViewById(R.id.desc);
                pDetails = findViewById(R.id.pDetails);
                desc.setText(description);
                spinner1.setSelection(pos1);
                spinner2.setSelection(pos2);
                pDetails.setText(details);
                ArrayAdapter<CharSequence> appAdapter = ArrayAdapter.createFromResource(getApplicationContext()
                        ,R.array.app, android.R.layout.simple_spinner_item);
                appAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(appAdapter);

                int ps1 = spinner1.getSelectedItemPosition();

                if (ps1 == 0){
                    ArrayAdapter<CharSequence> cat1 = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.cat1, android.R.layout.simple_spinner_item);

                    spinner2.setAdapter(cat1);
                }
                if (ps1 == 1){
                    ArrayAdapter<CharSequence> cat2 = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.cat2, android.R.layout.simple_spinner_item);

                    spinner2.setAdapter(cat2);
                }
                if (ps1 == 2){
                    ArrayAdapter<CharSequence> cat3 = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.cat3, android.R.layout.simple_spinner_item);

                    spinner2.setAdapter(cat3);
                }

            }
        }).start();

        delbtn = findViewById(R.id.log_delete);
        savebtn = findViewById(R.id.savebtn);
        archivebtn = findViewById(R.id.archivebtn);


        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final int id = getIntent().getIntExtra("id", 0);
                        final Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

                        AlertDialog del = new AlertDialog.Builder(getApplicationContext())
                                .setTitle("Delete")
                                .setMessage("Are you Sure you want to delete this log?" +
                                        "Your Log cannot be retrieved again")
                                .setIcon(getResources().getDrawable(R.drawable.ic_baseline_delete_forever_24))
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        MainActivity.logDatabase.logDao().delete(id);
                                        startActivity(intent1);
                                        Toast.makeText(getApplicationContext(), "Log Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();
                        del.show();

                    }
                }).start();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TextInputEditText desc;
                        EditText pDetails;
                        Spinner spinner1 = findViewById(R.id.app);
                        Spinner spinner2 = findViewById(R.id.cat);
                        final int id = getIntent().getIntExtra("id", 0);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        desc = findViewById(R.id.desc);
                        pDetails = findViewById(R.id.pDetails);
                        int pos1 = spinner1.getSelectedItemPosition();
                        int pos2 = spinner2.getSelectedItemPosition();

                        if (desc.getText().toString().isEmpty() || pDetails.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            MainActivity.logDatabase.logDao().save(desc.getText().toString(),
                                    pos1, pos2,
                                    pDetails.getText().toString()
                                    , id);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), " Your log has been recorded", Toast.LENGTH_SHORT).show();

                        }

                    }
                }).start();

            }
        });


        archivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog del = new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Archive")
                        .setMessage("Are you Sure you want to archive this log?" +
                                "Your Log cannot be edited again!")
                        .setIcon(getResources().getDrawable(R.drawable.ic_baseline_delete_forever_24))
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                spinner1.setEnabled(false);
                                spinner2.setEnabled(false);
                                desc.setEnabled(false);
                                pDetails.setEnabled(false);
                                Toast.makeText(getApplicationContext(), " Your log has been archived", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                del.show();

            }
        });


    }

}