package com.example.spidish.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spidish.MainActivity;
import com.example.spidish.MainProfile;
import com.example.spidish.MyHelper;
import com.example.spidish.R;
import com.example.spidish.adapter.SettingAdapter;
import com.example.spidish.model.ModelSetting;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    RecyclerView rcl_setting;
    ArrayList<ModelSetting> settinglist;
    CardView cv_logout;
    SQLiteDatabase  db;
    MyHelper ob;
    FirebaseAuth FAuth;
    FirebaseFirestore FStore;
    TextView tv_name, tv_mobile, tv_quali, tv_address, tv_gender, tv_email, tv_pcode;
    ImageView iv_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        rcl_setting=view.findViewById(R.id.rcl_setting);
        cv_logout = view.findViewById(R.id.cv_logout);
        tv_name = view.findViewById(R.id.tv_name);
        tv_mobile = view.findViewById(R.id.tv_mobile);
        tv_quali = view.findViewById(R.id.tv_quali);
        tv_address = view.findViewById(R.id.tv_address);
        tv_gender = view.findViewById(R.id.tv_gender);
        tv_email = view.findViewById(R.id.tv_email);
        tv_pcode = view.findViewById(R.id.tv_pcode);
        iv_profile = view.findViewById(R.id.iv_profile);

        ob = new MyHelper(getContext());

        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=ob.getWritableDatabase();
                db.execSQL("delete from login");
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        //For Setting Recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcl_setting.setLayoutManager(layoutManager);

        settinglist = new ArrayList<>();

        ModelSetting ms1 = new ModelSetting(R.drawable.logout,"LOGOUT");
        settinglist.add(ms1);

        rcl_setting.setAdapter(new SettingAdapter(settinglist));

        //For retrieve data from Google FireBase:
        FAuth = FirebaseAuth.getInstance();
        FStore = FirebaseFirestore.getInstance();

        DocumentReference DocRef = FStore.collection("users").document(FAuth.getCurrentUser().getUid());
        DocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    tv_name.setText(documentSnapshot.getString("Name"));
                    tv_mobile.setText(FAuth.getCurrentUser().getPhoneNumber());
                    tv_address.setText(documentSnapshot.getString("Address"));
                    tv_gender.setText(documentSnapshot.getString("Gender"));
                    tv_email.setText(documentSnapshot.getString("Email"));
                    tv_pcode.setText(documentSnapshot.getString("Pincode"));
                }
            }
        });


        return view;
    }
}