package com.example.spidish.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spidish.ProductDetails;
import com.example.spidish.R;
import com.example.spidish.SendOtp;
import com.example.spidish.model.ModelSummer;

import java.util.ArrayList;

public class SummerAdapter extends RecyclerView.Adapter<SummerAdapter.MyView> {
    private SummerAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(SummerAdapter.OnItemClickListener listener){
        onItemClickListener = listener;

    }

    Activity activity;
    ArrayList<ModelSummer> summerlist;

    public SummerAdapter(ArrayList<ModelSummer> summerlist) {
        this.summerlist = summerlist;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ui_summer,parent,false);
        return new SummerAdapter.MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.item_img.setImageResource(summerlist.get(position).getImg());
        holder.item_name.setText(summerlist.get(position).getName());
        holder.item_qty.setText(summerlist.get(position).getQuantity());
        holder.item_price.setText(summerlist.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return summerlist.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        ImageView item_img;
        TextView item_name,item_qty,item_price;
        public MyView(@NonNull View itemView) {
            super(itemView);

            item_img=itemView.findViewById(R.id.item_img);
            item_name=itemView.findViewById(R.id.item_name);
            item_qty=itemView.findViewById(R.id.item_qty);
            item_price=itemView.findViewById(R.id.item_price);


        }
    }
}
