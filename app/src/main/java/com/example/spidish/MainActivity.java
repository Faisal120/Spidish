package com.example.spidish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText txt_mob;
    Button btn_sendotp, btn_gmail, btn_fb, btn_google;
    TextView txt_skip;
    ProgressBar btn_pb1;
    Random rand;
    SQLiteDatabase db;
    MyHelper ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();
        ob=new MyHelper(getApplicationContext());
        txt_mob = findViewById(R.id.txt_mob);
        btn_sendotp = findViewById(R.id.btn_sendotp);
        btn_gmail = findViewById(R.id.btn_gmail);
        btn_fb = findViewById(R.id.btn_fb);
        btn_google = findViewById(R.id.btn_google);
        txt_skip = findViewById(R.id.txt_skip);
        btn_pb1 = findViewById(R.id.btn_pb1);

        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

        btn_sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mobile = txt_mob.getText().toString().trim();
                if (txt_mob.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    if (txt_mob.length() != 10) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid number ", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + txt_mob.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    btn_pb1.setVisibility(View.INVISIBLE);
                                    btn_sendotp.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    db = ob.getWritableDatabase();
                                    db.execSQL("insert into login(Mobile,Status)values('" + Mobile + "','LogIn')");

                                    Intent inten=new Intent(getApplicationContext(),SendOtp.class);
                                    inten.putExtra("mobile",txt_mob.getText().toString());
                                    inten.putExtra("backendotp",backendotp);
                                    startActivity(inten);
                                    Toast.makeText(getApplicationContext(), "OTP sent successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                    btn_pb1.setVisibility(View.VISIBLE);
                                    btn_sendotp.setVisibility(View.INVISIBLE);
                                }
                            }
                    );

                }
            }
        });
    }
}
