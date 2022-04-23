package com.example.spidish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.spidish.adapter.MustAdapter;
import com.example.spidish.adapter.MyAdapter;
import com.example.spidish.adapter.SummerAdapter;
import com.example.spidish.model.ModelAd;
import com.example.spidish.model.ModelMust;
import com.example.spidish.model.ModelSummer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView rcl_offerlist, rcl_musttry, rcl_summer;
    ArrayList<ModelSummer> mustlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rcl_offerlist=findViewById(R.id.rcl_offerlist);
        rcl_musttry=findViewById(R.id.rcl_must);
        rcl_summer=findViewById(R.id.rcl_summer);

        // for categories
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rcl_offerlist.setLayoutManager(layoutManager);
        MyAdapter dapter=new MyAdapter(dataqueue());
        rcl_offerlist.setAdapter(dapter);

        //for musttry items
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rcl_musttry.setLayoutManager(layoutManager2);
        MustAdapter adapter = new MustAdapter(datamodel());
        rcl_musttry.setAdapter(adapter);

        //for summer items
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rcl_summer.setLayoutManager(manager);
        SummerAdapter adapter2=new SummerAdapter(summer());
        rcl_summer.setAdapter(adapter2);



    }

     public ArrayList<ModelSummer> summer() {
        ArrayList<ModelSummer> summerdata= new ArrayList<>();

        ModelSummer ob1= new ModelSummer();
        ob1.setName("Coca Cola Refresh Soft Drink");
        ob1.setQuantity("300 ml");
        ob1.setPrice("₹ 40");
        ob1.setImg(R.drawable.coc);
        summerdata.add(ob1);

        ModelSummer ob2= new ModelSummer();
        ob2.setName("Schweppes Original Soda Water");
        ob2.setQuantity("250 ml");
        ob2.setPrice("₹ 60");
        ob2.setImg(R.drawable.soda);
        summerdata.add(ob2);

        ModelSummer ob3= new ModelSummer();
        ob3.setName("Maaza Mango Drink");
        ob3.setQuantity("150 ml");
        ob3.setPrice("₹ 10");
        ob3.setImg(R.drawable.maaza);
        summerdata.add(ob3);

        ModelSummer ob4= new ModelSummer();
        ob4.setName("Monster Energy Drink");
        ob4.setQuantity("350 ml");
        ob4.setPrice("₹ 110");
        ob4.setImg(R.drawable.mons);
        summerdata.add(ob4);

        ModelSummer ob5= new ModelSummer();
        ob5.setName("Schweppes Ginger Ale Soft Drink ");
        ob5.setQuantity("300 ml");
        ob5.setPrice("₹ 50");
        ob5.setImg(R.drawable.schweppes);
        summerdata.add(ob5);

        return summerdata;
    }

    public ArrayList<ModelMust> datamodel() {

        ArrayList<ModelMust> dataholder = new ArrayList<>();

        ModelMust ob2=new ModelMust();
        ob2.setName("Strawberry");
        ob2.setDescription("Strawberries increase HDL (good) cholesterol and lower your blood pressure.");
        ob2.setPrice("₹ 120");
        ob2.setQuantity("1");
        ob2.setUnit("KG");
        ob2.setImgurl(R.drawable.card2);
        ob2.getBigimg(R.drawable.b1);
        dataholder.add(ob2);

        ModelMust ob1=new ModelMust();
        ob1.setName("Kiwi");
        ob1.setDescription("Kiwis are high in Vitamin C and dietary fiber and provide a variety of health benefits");
        ob1.setPrice("₹ 60");
        ob1.setQuantity("1");
        ob1.setUnit("KG");
        ob1.setBigimg(R.drawable.b2);
        ob1.setImgurl(R.drawable.card1);
        dataholder.add(ob1);


        ModelMust ob3=new ModelMust();
        ob3.setName("Papaya");
        ob3.setDescription("Papaya contain high levels of antioxidants vitamin A, vitamin C and vitamin E");
        ob3.setPrice("₹ 100");
        ob3.setQuantity("1");
        ob3.setUnit("KG");
        ob3.setBigimg(R.drawable.b3);
        ob3.setImgurl(R.drawable.card3);
        dataholder.add(ob3);

        ModelMust ob4=new ModelMust();
        ob4.setName("Watermelon");
        ob4.setDescription("Watermelon helps to stay hydrated and have anticancer effects");
        ob4.setPrice("₹ 70");
        ob4.setQuantity("1");
        ob4.setUnit("KG");
        ob4.setBigimg(R.drawable.b4);
        ob4.setImgurl(R.drawable.card4);
        dataholder.add(ob4);
        return dataholder;
    }


    public ArrayList<ModelAd> dataqueue()
    {
        ArrayList<ModelAd> holder = new ArrayList<>();

        ModelAd ob2= new ModelAd();
        ob2.setId(1);
        ob2.setImg(R.drawable.offer_2);
        holder.add(ob2);

        ModelAd ob3=new ModelAd();
        ob3.setId(2);
        ob3.setImg(R.drawable.breakfast);
        holder.add(ob3);

        ModelAd ob4= new ModelAd();
        ob4.setId(3);
        ob4.setImg(R.drawable.offer_2);
        holder.add(ob4);

        ModelAd ob5=new ModelAd();
        ob5.setId(4);
        ob5.setImg(R.drawable.fru);
        holder.add(ob5);
        return holder;

    }
}