package com.example.itcbugtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LogActivity extends AppCompatActivity {
    int id = getIntent().getIntExtra("id", 0);
    int pos1 = getIntent().getIntExtra("category1", 0);
    int pos2 = getIntent().getIntExtra("category2", 0);
    boolean flag = getIntent().getBooleanExtra("archived", false);
    String content = getIntent().getStringExtra("desc");
    String details = getIntent().getStringExtra("pDetails");

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


        if (flag) {
            spinner1.setEnabled(false);
            spinner2.setEnabled(false);
            desc.setEnabled(false);
            pDetails.setEnabled(false);

        }


        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        final int id = getIntent().getIntExtra("id", 0);


        new Thread(new Runnable() {
            @Override
            public void run() {
                int pos1 = getIntent().getIntExtra("category1", 0);
                int pos2 = getIntent().getIntExtra("category2", 0);
                String content = getIntent().getStringExtra("desc");
                String details = getIntent().getStringExtra("pDetails");
                TextInputEditText desc;
                EditText pDetails;
                Spinner spinner1;
                Spinner spinner2;
                spinner1 = findViewById(R.id.app);
                spinner2 = findViewById(R.id.cat);
                desc = findViewById(R.id.desc);
                pDetails = findViewById(R.id.pDetails);
                desc.setText(content);
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
                spinner1.setSelection(pos1, true);
                spinner2.setSelection(pos2, true);

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
                        int id = getIntent().getIntExtra("id", 0);
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

                        MainActivity.logDatabase.logDao().delete(id);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Log Deleted", Toast.LENGTH_SHORT).show();

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
                        int id = getIntent().getIntExtra("id", 0);
                        boolean archived = intent.getBooleanExtra("archived", false);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        desc = findViewById(R.id.desc);
                        pDetails = findViewById(R.id.pDetails);
                        int pos1 = spinner1.getSelectedItemPosition();
                        int pos2 = spinner2.getSelectedItemPosition();
                        String details = pDetails.getText().toString();
                        String description = desc.getText().toString();
                        Log log = new Log();
                        log.setCategory1(pos1);
                        log.setCategory2(pos2);
                        log.setArchived(archived);
                        log.setDesc(details);
                        log.setpDetails(description);


                        if (desc.getText().toString().isEmpty() || pDetails.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Enter All details", Toast.LENGTH_LONG).show();

                        }
                        else{
                            MainActivity.logDatabase.logDao().save(log);
                            startActivity(intent);
                            Toast toast = Toast.makeText(getApplicationContext(), " Your log has been recorded", Toast.LENGTH_SHORT);
                            toast.show();

                        }

                    }
                }).start();

            }
        });


        archivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Archive")
                        .setMessage("Are you Sure you want to archive this log?" +
                                "\nYour Log cannot be edited again!")
                        .setIcon(getResources().getDrawable(R.drawable.ic_baseline_delete_forever_24))
                        .setPositiveButton("Archive", new DialogInterface.OnClickListener() {
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
                        }).create().show();

                flag = true;


            }
        });


    }

}