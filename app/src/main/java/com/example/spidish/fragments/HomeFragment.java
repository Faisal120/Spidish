package com.example.spidish.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.spidish.MainActivity;
import com.example.spidish.R;
import com.example.spidish.adapter.MustAdapter;
import com.example.spidish.adapter.MyAdapter;
import com.example.spidish.adapter.SummerAdapter;
import com.example.spidish.fragments.CategoryFragment;
import com.example.spidish.model.ModelAd;
import com.example.spidish.model.ModelMust;
import com.example.spidish.model.ModelSummer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rcl_offerlist, rcl_musttry, rcl_summer;
    ArrayList<ModelSummer> summerlist;
    ArrayList<ModelAd> offerlist;
    ArrayList<ModelMust> mustlist;
    CardView cv_allcat;
    Context getApplicationContext;
    RelativeLayout relativeLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        rcl_offerlist=view.findViewById(R.id.rcl_offerlist);
        rcl_musttry=view.findViewById(R.id.rcl_must);
        rcl_summer=view.findViewById(R.id.rcl_summer);
        cv_allcat = view.findViewById(R.id.cv_allcat);
        relativeLayout = view.findViewById(R.id.relativelayout);

        cv_allcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment catfrag=new Fragment();
                FragmentTransaction fm= getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.relativelayout, catfrag).commit();
            }
        });

        // for offerlist
        RecyclerView.LayoutManager layoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcl_offerlist.setLayoutManager(layoutManager2);

        offerlist = new ArrayList<>();

        ModelAd ob1=new ModelAd(1,R.drawable.offer_2);
        offerlist.add(ob1);

        ModelAd ob2=new ModelAd(2,R.drawable.breakfast);
        offerlist.add(ob2);

        ModelAd ob3=new ModelAd(3,R.drawable.fru);
        offerlist.add(ob3);

        ModelAd ob4=new ModelAd(3,R.drawable.offer_2);
        offerlist.add(ob4);


        rcl_offerlist.setAdapter(new MyAdapter(offerlist));

        //for musttry items
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcl_musttry.setLayoutManager(layoutManager);

        mustlist=new ArrayList<>();
        ModelMust must1= new ModelMust("Strawberry","Strawberries increase HDL (good) cholesterol and lower your blood pressure.","₹ 120","1","KG",R.drawable.card2);
        mustlist.add(must1);

        ModelMust must2= new ModelMust("Kiwi","Kiwis are high in Vitamin C and dietary fiber and provide a variety of health benefits","₹ 60","1","KG",R.drawable.card1);
        mustlist.add(must2);

        ModelMust must3= new ModelMust("Papaya","Papaya contain high levels of antioxidants vitamin A, vitamin C and vitamin E","₹ 100","1","KG",R.drawable.card3);
        mustlist.add(must3);

        ModelMust must4= new ModelMust("Watermelon","Strawberries increase HDL (good) cholesterol and lower your blood pressure.","₹ 70","1","KG",R.drawable.card4);
        mustlist.add(must4);

        rcl_musttry.setAdapter(new MustAdapter(mustlist));

        //for summer items
        RecyclerView.LayoutManager summerlayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcl_summer.setLayoutManager(summerlayout);

        summerlist=new ArrayList<>();

        ModelSummer sb1=new ModelSummer("Coca Cola Refresh Soft Drink","300 ml","₹ 40",R.drawable.coc);
        summerlist.add(sb1);

        ModelSummer sb2=new ModelSummer("Schweppes Original Soda Water","250 ml","₹ 60",R.drawable.soda);
        summerlist.add(sb2);

        ModelSummer sb3=new ModelSummer("Maza Mango Juice ","150 ml","₹ 10",R.drawable.maaza);
        summerlist.add(sb3);

        ModelSummer sb4=new ModelSummer("Monster Energy Drink","350 ml","₹ 110",R.drawable.mons);
        summerlist.add(sb4);

        ModelSummer sb5=new ModelSummer("Schweppes Ginger Ale Soft Drink","300 ml","₹ 50",R.drawable.schweppes);
        summerlist.add(sb5);

        rcl_summer.setAdapter(new SummerAdapter(summerlist));

        return view;
    }

}