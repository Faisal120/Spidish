package com.example.spidish.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spidish.MainActivity;
import com.example.spidish.R;
import com.example.spidish.model.ModelSetting;

import java.util.ArrayList;
import java.util.Set;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHold> {

    private SettingAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(SettingAdapter.OnItemClickListener listener){
        onItemClickListener = listener;

    }

    ArrayList<ModelSetting> data;
    Activity activity;

    public SettingAdapter(ArrayList<ModelSetting> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater11 = LayoutInflater.from(parent.getContext());
        View view1=inflater11.inflate(R.layout.ui_setting,parent,false);
        return new ViewHold(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {

        holder.img.setImageResource(data.get(position).getImg());
        holder.txt.setText(data.get(position).getTxt());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        CardView cv_setting;
        ImageView img;
        TextView txt;

        public ViewHold(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.setting_img);
            txt = itemView.findViewById(R.id.setting_txt);
            cv_setting=itemView.findViewById(R.id.cv_set);

        }
    }
}
