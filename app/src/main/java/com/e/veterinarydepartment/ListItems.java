package com.e.veterinarydepartment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public  class ListItems extends RecyclerView.Adapter<ListItems.ProgrammingViewHolder> {
    ArrayList<String> arrayList;
    private Context context;


    public ListItems(Context context,ArrayList arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.testing_layout,viewGroup,false);
        return new ProgrammingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder viewHolder, int i) {
        String name;
        name=arrayList.get(i);

        viewHolder.textView.setText(name);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ProgrammingViewHolder(View view) {
            super(view);
            textView=view.findViewById(R.id.textView);

        }
    }
}
