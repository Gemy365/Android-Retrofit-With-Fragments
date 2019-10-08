package com.example.android.retrofitwithfragments;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofitwithfragments.Model.Data;

import java.util.List;

// RecyclerViewAdapter class extends RecyclerView.Adapter<This Class.Internal Class Into This Class>{
class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    // Init.
    Context context;
    List<Data> dataList;

    // Constructor Take 2 Param (Context, List Of Data).
    public RecyclerViewAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Connect Class With item_get_data Xml.
        View view = LayoutInflater.from(context).inflate(R.layout.item_get_data, viewGroup, false);

        // Call Internal Class & Send view As Param To Constructor.
        MyViewHolder myViewHolder = new MyViewHolder(view);

        // return.
        return myViewHolder;
    }

    // Method To Active Views That Into MyViewHolder Internal Class.
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        // append .. Concatenate Texts Together.
        myViewHolder.Result.append("ID " + dataList.get(i).getId() + "\n");
        myViewHolder.Result.append("UserID: " + dataList.get(i).getUserId() + "\n");
        myViewHolder.Result.append("Title " + dataList.get(i).getTitle() + "\n");
        myViewHolder.Result.append("Text " + dataList.get(i).getText() + "\n");

    }

    // Method Return Size of Repeated Data.
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // MyViewHolder Internal Class extends RecyclerView.ViewHolder.
    public class MyViewHolder extends RecyclerView.ViewHolder{

        // Init View.
        TextView Result;

        // Constructor.
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find View By ID.
            Result = (TextView) itemView.findViewById(R.id.text_get_fragment);

        }
    }
}
