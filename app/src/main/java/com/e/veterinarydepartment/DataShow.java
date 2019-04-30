package com.e.veterinarydepartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataShow extends AppCompatActivity {

    Button all,cast,prag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datashow);

        all = findViewById(R.id.all_data);
        cast = findViewById(R.id.castiation_data);
        prag = findViewById(R.id.pragrancy_data);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataShow.this, allAnimalData.class);
                startActivity(intent);
            }
        });

        cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataShow.this, AnimalDataActivity.class);
                intent.putExtra("AnimalChoice","बड़ा");
                startActivity(intent);
            }
        });

        prag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent = new Intent(DataShow.this, AnimalDataActivity.class);
                intent.putExtra("AnimalChoice","बड़ा");
                startActivity(intent);
            }
        });
    }
}
