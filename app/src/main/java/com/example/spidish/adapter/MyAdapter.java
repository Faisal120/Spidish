package com.example.spidish.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spidish.Dashboard;
import com.example.spidish.R;
import com.example.spidish.model.ModelAd;
import com.example.spidish.model.ModelMust;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyView>
{
    ArrayList<ModelAd> data;

    public MyAdapter(ArrayList<ModelAd> data)
    {
        this.data = data;
    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ui_ad,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.img.setImageResource(data.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyView extends RecyclerView.ViewHolder
    {
        ImageView img;
        public MyView(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_offer);
        }
    }
}
