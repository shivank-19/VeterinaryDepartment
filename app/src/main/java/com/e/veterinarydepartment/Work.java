package com.e.veterinarydepartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Work extends AppCompatActivity {

    private Button med,cas,vac, prag;
    private String Id,Aname,Asize;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        med = findViewById(R.id.btn_medic);
        cas = findViewById(R.id.castiation);
        vac = findViewById(R.id.vacination);
        prag = findViewById(R.id.pragnancy);


        intent=getIntent();
        Id=intent.getStringExtra("uid");
        Asize=intent.getStringExtra("size");
        Aname=intent.getStringExtra("name");

        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         intent   = new Intent(Work.this, Medic.class);
                intent.putExtra("uid",Id);

                intent.putExtra("size",Asize);

                intent.putExtra("name",Aname);

                startActivity(intent);
            }
        });


    }
}
