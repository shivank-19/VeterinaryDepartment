package com.e.veterinarydepartment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class Castiation_activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AnimalData> animalDataList = new ArrayList<>();
    private List<AnimalData> animalDataList2= new ArrayList<>();

    private ArrayList<String> arrayList,arrayList2;
    private DatabaseReference firebaseDatabase;
    private ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castiation);
        recyclerView = findViewById(R.id.recyclerView);
        toggleButton=findViewById(R.id.toggleButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        arrayList2=new ArrayList<>();


        toggleButton.setText("All Data");
        toggleButton.setTextOff("All Data");
        toggleButton.setTextOn("Today\nData");


        firebaseDatabase = FirebaseDatabase.getInstance().getReference("ANIMAL").child("बड़ा");
        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AnimalData animalData = snapshot.getValue(AnimalData.class);
                    if (animalData.getGender().equals("नर") && (
                            animalData.getTime().contains( new SimpleDateFormat("MM/dd/yyyy").format(new Date()))  ) ){
                          animalDataList.add(animalData);
                          arrayList.add(snapshot.getKey());
                    }
                    if (animalData.getGender().equals("नर")){
                        animalDataList2.add(animalData);
                        arrayList2.add(snapshot.getKey());
                    }
                }

                ListItems listItems = new ListItems(Castiation_activity.this, animalDataList2, arrayList2);
                recyclerView.setAdapter(listItems);
                listItems.notifyDataSetChanged();

                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                        if (isChecked){

                            ListItems listItems = new ListItems(Castiation_activity.this, animalDataList, arrayList);
                            recyclerView.setAdapter(listItems);
                            listItems.notifyDataSetChanged();
                        }
                        else {
                            ListItems listItems = new ListItems(Castiation_activity.this, animalDataList2, arrayList2);
                            recyclerView.setAdapter(listItems);
                            listItems.notifyDataSetChanged();
                        }
                    }
                });

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
