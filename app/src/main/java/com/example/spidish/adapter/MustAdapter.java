package com.example.spidish.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spidish.Dashboard;
import com.example.spidish.ProductDetails;
import com.example.spidish.R;
import com.example.spidish.model.ModelMust;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MustAdapter extends RecyclerView.Adapter<MustAdapter.ViewHolder> {


    Context activity;

    ArrayList<ModelMust> mustlist;

    public MustAdapter(ArrayList<ModelMust> mustlist) {
        this.mustlist = mustlist;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ui_musttry,parent,false);
        return new MustAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ModelMust temp = mustlist.get(position);
        holder.prod_name.setText(mustlist.get(position).getName());
        holder.prod_des.setText(mustlist.get(position).getDescription());
        holder.prod_price.setText(mustlist.get(position).getPrice());
        holder.prod_qty.setText(mustlist.get(position).getQuantity());
        holder.prod_unit.setText(mustlist.get(position).getUnit());
        holder.prod_img.setImageResource(mustlist.get(position).getImgurl());

        holder.prod_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,ProductDetails.class);
                intent.putExtra("name",temp.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mustlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv_musttry;
        ImageView prod_img;
        TextView prod_name,prod_des,prod_price,prod_qty,prod_unit;
        ConstraintLayout layoutsum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_musttry=itemView.findViewById(R.id.cv_musttry);
            prod_img=itemView.findViewById(R.id.prod_img);
            prod_name=itemView.findViewById(R.id.prod_name);
            prod_des=itemView.findViewById(R.id.prod_des);
            prod_price=itemView.findViewById(R.id.prod_price);
            prod_qty=itemView.findViewById(R.id.prod_qty);
            prod_unit=itemView.findViewById(R.id.prod_unit);
            layoutsum=itemView.findViewById(R.id.layoutsum);


        }
    }
}
