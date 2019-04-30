package com.e.veterinarydepartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class allAnimalData extends AppCompatActivity {

    Button large,small, birds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_animal_data);
        large = findViewById(R.id.large_animal);
        small = findViewById(R.id.Small_animal);
        birds = findViewById(R.id.birds);

        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(allAnimalData.this, AnimalDataActivity.class);
                intent.putExtra("AnimalChoice","बड़ा");
                startActivity(intent);

            }
        });

        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(allAnimalData.this, AnimalDataActivity.class);
                intent.putExtra("AnimalChoice","छोटा");
                startActivity(intent);

            }
        });

        birds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(allAnimalData.this, AnimalDataActivity.class);
                intent.putExtra("AnimalChoice","पक्षी");
                startActivity(intent);

            }
        });
    }
}
