package com.shayari_jokesallinone.RecyclerPackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.shayari_jokesallinone.DetailActivity;
import com.shayari_jokesallinone.R;
import com.shayari_jokesallinone.ShayariOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class DbAdapter extends RecyclerView.Adapter<DbAdapter.DbViewHolder> {

    List<DbModelClass> objDbModelClassesArrayList;
    ShayariOnClickListener shayariOnClickListener;

    public DbAdapter(List<DbModelClass> objDbModelClassesArrayList,ShayariOnClickListener onClickListener) {
        this.objDbModelClassesArrayList = objDbModelClassesArrayList;
        this.shayariOnClickListener=onClickListener;
    }

    @NonNull
    @Override
    public DbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row,parent,false);
        return new DbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DbViewHolder holder, final int position) {
        final DbModelClass objDbModelClass = objDbModelClassesArrayList.get(position);
        final String mydata = objDbModelClass.getShayari();
        holder.txt_row.setText(mydata);
        holder.constraintRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click",""+objDbModelClassesArrayList);
                shayariOnClickListener.onClicked(holder.getAdapterPosition(),objDbModelClassesArrayList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objDbModelClassesArrayList.size();
    }

    public static class DbViewHolder extends RecyclerView.ViewHolder{

        TextView txt_row;
        ConstraintLayout constraintRow;

        public DbViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_row = itemView.findViewById(R.id.txt_row);
            constraintRow = itemView.findViewById(R.id.constraint_row);
        }
    }
}

