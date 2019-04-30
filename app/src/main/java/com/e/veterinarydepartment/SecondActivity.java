package com.e.veterinarydepartment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    RadioGroup radioGroup, gender;
    RadioButton animal, birds, male, female;
    Spinner animal_type, large_animal, Month, Year;
    Button button;
    String s3;
    ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4, adapter5, adapter6;
    List<String> smallanimallist = new ArrayList<>();
    List<String> largeanimallist = new ArrayList<>();
    String[] array1, array2, array3, array4, array5,array6;
    private EditText Phone;
    String Atype, Aname, Agender, Aage, APhone, Asize, Amonth, Ayear;
    private DatabaseReference firebaseDatabase;
    private AnimalData animalData;
    private String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Phone = findViewById(R.id.editTextPhone);
        radioGroup = findViewById(R.id.rg_animal);
        animal = findViewById(R.id.rb_animal);
        birds = findViewById(R.id.rb_birds);
        animal_type = findViewById(R.id.sp_animal_type);
        button = findViewById(R.id.submit);
        gender = findViewById(R.id.rg_gender);
        male = findViewById(R.id.rb_male);
        female = findViewById(R.id.rb_female);
        large_animal = findViewById(R.id.sp_large_animal);
        Month = findViewById(R.id.sp_month);
        Year = findViewById(R.id.sp_year);
        //ID Generated
        Id=FirebaseDatabase.getInstance().getReference().push().getKey();
        //Toast.makeText(this, ""+Id, Toast.LENGTH_SHORT).show();
        array1 = getResources().getStringArray(R.array.animals_type);
        array2 = getResources().getStringArray(R.array.small_animal_name);
        array3 = getResources().getStringArray(R.array.large_animal_name);
        array4 = getResources().getStringArray(R.array.months);
        array5 = getResources().getStringArray(R.array.year);
        array6 = getResources().getStringArray(R.array.birds);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array5);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array6);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animal_type.setAdapter(adapter1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_birds)
                {
                    animal_type.setEnabled(false);
                    large_animal.setAdapter(adapter6);
                    Atype = "पक्षी";
                    Asize = "पक्षी";
                }
                else
                    {
                    animal_type.setEnabled(true);
                    large_animal.setEnabled(true);
                    Atype = "पशु";
                        animal_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Asize = parent.getItemAtPosition(position).toString().trim();
                                if (parent.getItemAtPosition(position).toString().equals("छोटा")) {
                                    large_animal.setAdapter(adapter2);
                                }
                                if (parent.getItemAtPosition(position).toString().equals("बड़ा")) {
                                    large_animal.setAdapter(adapter3);
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Toast.makeText(SecondActivity.this, "", Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            }
        });
        large_animal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Aname = large_animal.getItemAtPosition(position).toString().trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_male) {
                    Agender = "नर";
                } else {
                    Agender = "मादा";
                }
            }
        });
        Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Amonth = parent.getItemAtPosition(position).toString().trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ayear = parent.getItemAtPosition(position).toString().trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPhone(Phone.getText().toString())){
                    Phone.setError("इसे खाली मत रखो");
                }
                else if (checkAnimalType(animal,birds)) {
                    Toast.makeText(SecondActivity.this, "Select the Animal or Bird!", Toast.LENGTH_SHORT).show();
                } //else if (checkAnimalSize(animal_type.getSelectedItem().toString())){
                   // Toast.makeText(SecondActivity.this, "Select the Animal Type!", Toast.LENGTH_SHORT).show();}
                 else if (checkAnimalName(large_animal.getSelectedItem().toString())) {
                    Toast.makeText(SecondActivity.this, "Select Animal Name!", Toast.LENGTH_SHORT).show();
                } else if (checkGender(male,female)) {
                    Toast.makeText(SecondActivity.this, "Select the Gender!", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseDatabase = FirebaseDatabase.getInstance().getReference("ANIMAL").child("" + Asize).child("" + Aname);
                    animalData = new AnimalData(Phone.getText().toString().trim(), Atype, Aname, Agender, Amonth + " महीना " + Ayear
                            + " साल", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
                    firebaseDatabase.child("" + Id).setValue(animalData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Intent intent = new Intent(SecondActivity.this, Work.class);
                            intent.putExtra("uid", Id);
                            intent.putExtra("size", Asize);
                            intent.putExtra("name", Aname);
                            startActivity(intent);
                            Toast.makeText(SecondActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SecondActivity.this, "Data not send", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public Boolean checkPhone(String s){
        Boolean result=false;
        if (s.equals("")){
            result=true;
        }
        return result;
    }
    public Boolean checkAnimalType(RadioButton x,RadioButton y){
        Boolean result=false;
        if (x.isChecked()==false&&y.isChecked()==false){
            result=true;
        }
        return result;
    }
    public Boolean checkAnimalSize(String s){
        Boolean result=false;
        if (s.equals("पशु के प्रकार का चयन करें")){
            result=true;
        }
        return result;
    }
    public Boolean checkAnimalName(String s){
        Boolean result=false;
        if (s.equals("बड़ा पशु का चयन करें")){
            result=true;
        }
        if (s.equals("छोटा पशु का चयन करें")) {
            result=true;
        }
        if (s.equals("पक्षी का चयन करे")) {
            result=true;
        }
        return result;
    }
    public Boolean checkGender(RadioButton x,RadioButton y){
        Boolean result=false;
        if (x.isChecked()==false&&y.isChecked()==false){
            result=true;
        }
        return result;
    }


}
