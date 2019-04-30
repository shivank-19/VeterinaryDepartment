package com.e.veterinarydepartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Final extends AppCompatActivity {

    Button entry,report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        entry = findViewById(R.id.entry);
        report = findViewById(R.id.report);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Final.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Final.this, DataShow.class);
                startActivity(intent);
            }
        });
    }
}
