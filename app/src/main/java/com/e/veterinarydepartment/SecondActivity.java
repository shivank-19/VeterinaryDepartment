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

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    RadioGroup radioGroup, gender;
    RadioButton animal, birds, male, female;
    Spinner animal_type, large_animal, Month, Year;
    Button button;
    String s3;
    ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4, adapter5;
    List<String> smallanimallist = new ArrayList<>();
    List<String> largeanimallist = new ArrayList<>();
    String[] array1, array2, array3, array4, array5;
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
        /*Toast.makeText(this, ""+Id, Toast.LENGTH_SHORT).show();
*/
        
        
        array1 = getResources().getStringArray(R.array.animals_type);
        array2 = getResources().getStringArray(R.array.small_animal_name);
        array3 = getResources().getStringArray(R.array.large_animal_name);
        array4 = getResources().getStringArray(R.array.months);
        array5 = getResources().getStringArray(R.array.year);


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


        animal_type.setAdapter(adapter1);

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


        large_animal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Aname = large_animal.getItemAtPosition(position).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_birds) {

                    animal_type.setEnabled(false);
                    large_animal.setEnabled(false);
                    Atype = "पक्षी";

                } else {
                    animal_type.setEnabled(true);
                    large_animal.setEnabled(true);
                    Atype = "पशु";
                }
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

                if (Phone.getText().toString().isEmpty()) {
                    Phone.setError("इसे खाली मत रखो");
                    return;
                }
               else {
                    firebaseDatabase = FirebaseDatabase.getInstance().getReference("ANIMAL").child("" + Asize).child("" + Aname);
                    animalData = new AnimalData(Phone.getText().toString().trim(), Atype, Aname, Agender, Amonth + "" + Ayear);

                    firebaseDatabase.child("" + Id).setValue(animalData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(SecondActivity.this, Work.class);
                            intent.putExtra("uid", Id);
                            intent.putExtra("size", Asize);

                            intent.putExtra("name", Aname);

                            startActivity(intent);
                            Toast.makeText(SecondActivity.this, "Succes", Toast.LENGTH_SHORT).show();
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
}

