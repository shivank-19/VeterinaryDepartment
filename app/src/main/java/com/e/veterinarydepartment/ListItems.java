package com.e.veterinarydepartment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ListItems extends RecyclerView.Adapter<ListItems.ProgrammingViewHolder> {


    private Boolean aBoolean=true;
    private ArrayList<String> list = new ArrayList<>();
    private Context context;
    private List<AnimalData> arrayList = new ArrayList<>();
    private DatabaseReference firebaseDatabase;
    private String sexCheck;


    public ListItems(Context context, List arrayList, ArrayList list) {
        this.context = context;
        this.list = list;
        this.arrayList = arrayList;
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("ANIMAL").child("बड़ा");
        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AnimalData animalData = snapshot.getValue(AnimalData.class);
                    if (animalData.getGender().equals("नर") && (
                            animalData.getTime().contains( new SimpleDateFormat("MM/dd/yyyy").format(new Date()))  ) ){

                    }
                    if (animalData.getGender().equals("नर")){

                    }
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

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.testing_layout, viewGroup, false);
        return new ProgrammingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder viewHolder, final int i) {
        final AnimalData name,sex;
        String k;
        name = arrayList.get(i);

        k = list.get(i);
        viewHolder.textView.setText("Name= " + name.getAnimalname() + "\nkey " + list.get(i) + "\n" + name.getAge() + "\nPhone= " + name.getPhone());
  viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

          if (name.getGender().equals("मादा")){
              if (isChecked){
                  firebaseDatabase.child(name.getAnimalname()).child(list.get(viewHolder.getAdapterPosition())).child("pragnancy").setValue(isChecked);
                  Toast.makeText(context, "c"+list.get(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
              }
              else
              {
                  firebaseDatabase.child(name.getAnimalname()).child(list.get(viewHolder.getAdapterPosition())).child("pragnancy").setValue(false);

                  Toast.makeText(context, "c"+list.get(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
              }
          }else {
              if (isChecked){
                  firebaseDatabase.child(name.getAnimalname()).child(list.get(viewHolder.getAdapterPosition())).child("castiation").setValue(isChecked);
                  Toast.makeText(context, "c"+list.get(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
              }
              else
              {
                  firebaseDatabase.child(name.getAnimalname()).child(list.get(viewHolder.getAdapterPosition())).child("castiation").setValue(false);

                  Toast.makeText(context, "c"+list.get(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
              }
          }


      }
  });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public ProgrammingViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            checkBox = view.findViewById(R.id.checkbox);
        }
    }
}
