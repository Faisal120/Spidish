package com.example.spidish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class User_Profile extends AppCompatActivity {

    EditText txt_mob,txt_name,txt_mail,txt_address,txt_pcode;
    RadioButton rb_male,rb_female;
    Button btn_register;
    String mob="";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txt_mob=findViewById(R.id.txt_mob);
        txt_name=findViewById(R.id.txt_name);
        txt_mail=findViewById(R.id.txt_mail);
        txt_address=findViewById(R.id.txt_address);
        txt_pcode=findViewById(R.id.txt_pcode);
        rb_male=findViewById(R.id.rb_male);
        rb_female=findViewById(R.id.rb_female);
        btn_register=findViewById(R.id.btn_register);

        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        String userID = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(userID);

        mob=getIntent().getStringExtra("mobile");
        txt_mob.setText(mob);
        txt_mob.setEnabled(false);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mobile=txt_mob.getText().toString();
                String Name=txt_name.getText().toString();
                String Email=txt_mail.getText().toString();
                String Address=txt_address.getText().toString();
                String Pincode=txt_pcode.getText().toString();
                String Gender=rb_male.isChecked()?"Male":"Female";

                if(txt_name.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    txt_name.requestFocus();
                }
                else if(txt_mail.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    txt_mail.requestFocus();
                }
                else if(txt_address.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your address", Toast.LENGTH_SHORT).show();
                    txt_address.requestFocus();
                }
                else if (txt_pcode.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your area pincode", Toast.LENGTH_SHORT).show();
                    txt_pcode.requestFocus();
                }
                else if(rb_male.isChecked()==false &&  rb_female.isChecked()==false)
                {
                    Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Map<String,Object> user = new HashMap<>();
                    user.put("Mobile",Mobile);
                    user.put("Name",Name);
                    user.put("Email",Email);
                    user.put("Address",Address);
                    user.put("Pincode",Pincode);
                    user.put("Gender",Gender);

                    documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                intent.putExtra("mobile",mob);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}