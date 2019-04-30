package com.e.veterinarydepartment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnimalDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private List<AnimalData> animalDataList = new ArrayList<>();
    private List<AnimalData> animalDataList2= new ArrayList<>();

    private ArrayList<String> arrayList,arrayList2;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_data);

        recyclerView=findViewById(R.id.rv_animal_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        arrayList2=new ArrayList<>();



        firebaseDatabase = FirebaseDatabase.getInstance().getReference("ANIMAL").child(getIntent().getStringExtra("AnimalChoice"));
        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AnimalData animalData = snapshot.getValue(AnimalData.class);
                        animalDataList.add(animalData);
                        arrayList.add(snapshot.getKey());
                    AllDataAdapter allDataAdapter = new AllDataAdapter(getApplicationContext(), animalDataList, arrayList);

                    recyclerView.setAdapter(allDataAdapter);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
