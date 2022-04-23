package com.example.spidish;

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

public class User_Profile extends AppCompatActivity {

    EditText txt_mob,txt_name,txt_mail,txt_address,txt_pcode;
    RadioButton rb_male,rb_female;
    Button btn_register;
    String mob="";


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
                    StringRequest req=new StringRequest(Request.Method.POST, "https://nexes.info/Faisal/Spidish/spi_profile.php?m1=" + Mobile + "&m2=" + Name + "&m3=" + Email + "&m4=" + Address + "&m5=" + Pincode + "&m6=" + Gender, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("Profile Created"))
                            {
                                Intent inten1=new Intent(getApplicationContext(),Dashboard.class);
                                Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_SHORT).show();
                                startActivity(inten1);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    RequestQueue r1= Volley.newRequestQueue(getApplicationContext());
                    r1.add(req);
                }
            }
        });
    }
}