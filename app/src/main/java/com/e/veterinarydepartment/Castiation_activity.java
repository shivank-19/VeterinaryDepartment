package com.e.veterinarydepartment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class Castiation_activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    private DatabaseReference firebaseDatabase,fdb;
    //private AnimalData animalData,narData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castiation);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<>();
        firebaseDatabase= FirebaseDatabase.getInstance().getReference("ANIMAL").child("बड़ा");

        final ListItems listItems=new ListItems(this,arrayList);

        recyclerView.setAdapter(listItems);

        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             //   arrayList.add(dataSnapshot.getValue().toString());
            for (DataSnapshot snapshot:dataSnapshot.getChildren()){
               // AnimalData animalData = snapshot.getValue(AnimalData.class);
               // arrayList.add(snapshot.child("gender").getValue().toString());
                arrayList.add(snapshot.getKey());

                //arrayList.add(animalData.getAnimalname());
            }

                listItems.notifyDataSetChanged();
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
