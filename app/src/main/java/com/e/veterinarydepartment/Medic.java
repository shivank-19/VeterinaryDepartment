package com.e.veterinarydepartment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Medic extends AppCompatActivity {

    private AnimalData animalData;
    private TextView id,size,name,gender,age, mobile;
    private TextInputEditText dis, medi;
    private Button save;
    String Id,Asize,Aname;
    Intent intent;

    private DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);


        id = findViewById(R.id.ID);
        size = findViewById(R.id.Size);
        name = findViewById(R.id.Name);
        gender = findViewById(R.id.Gender);
        age = findViewById(R.id.Age);
        mobile = findViewById(R.id.Mobile);
        dis = findViewById(R.id.disease);
        medi = findViewById(R.id.medicine);
        save = findViewById(R.id.save);

        intent=getIntent();
        Id=intent.getStringExtra("uid");
        Asize=intent.getStringExtra("size");
        Aname=intent.getStringExtra("name");

        id.setText(Id);
        size.setText(Asize);
        name.setText(Aname);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference("ANIMAL").child(""+Asize).child(""+Aname).child(""+Id);
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                animalData = dataSnapshot.getValue(AnimalData.class);
               //Toast.makeText(Medic.this, String.format("%s", dataSnapshot.getValue(AnimalData.class).getGender()), Toast.LENGTH_SHORT).show();

                gender.setText(animalData.getGender());
                age.setText(animalData.getAge());
                mobile.setText(animalData.getPhone());

                gender.setText(dataSnapshot.child("gender").getValue().toString());
                age.setText(dataSnapshot.child("age").getValue().toString());
                mobile.setText(dataSnapshot.child("phone").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dis.getText().toString().isEmpty()) {
                    dis.setError("Dont keep it Empty");
                    return;
                }
                if (medi.getText().toString().isEmpty()) {
                    medi.setError("Dont keep it Empty");
                    return;
                } else {
                    firebaseDatabase.child("Disease").setValue(dis.getText().toString());
                    firebaseDatabase.child("Medicine").setValue(medi.getText().toString());
                    Intent intent = new Intent(Medic.this,SecondActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
