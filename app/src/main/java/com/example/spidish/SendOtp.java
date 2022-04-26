package com.example.spidish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.provider.FirebaseInitProvider;

import java.util.concurrent.TimeUnit;

public class SendOtp extends AppCompatActivity {
    EditText txt_otp1, txt_otp2, txt_otp3, txt_otp4, txt_otp5, txt_otp6;
    TextView txt_reotp, txt_txtview;
    Button btn_verifyotp;
    ProgressBar btn_pb2;
    String Mobile = "";
    String getbackend;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        txt_reotp = findViewById(R.id.txt_reotp);
        txt_otp1 = findViewById(R.id.txt_otp1);
        txt_otp2 = findViewById(R.id.txt_otp2);
        txt_otp3 = findViewById(R.id.txt_otp3);
        txt_otp4 = findViewById(R.id.txt_otp4);
        txt_otp5 = findViewById(R.id.txt_otp5);
        txt_otp6 = findViewById(R.id.txt_otp6);
        txt_reotp = findViewById(R.id.txt_reotp);
        btn_pb2 = findViewById(R.id.btn_pb2);
        btn_verifyotp = findViewById(R.id.btn_verifyotp);
        txt_txtview = findViewById(R.id.txt_txtview);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        Intent ob2 = getIntent();
        Mobile = ob2.getStringExtra("mobile");

        txt_txtview.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getbackend = getIntent().getStringExtra("backendotp");


        btn_verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txt_otp1.getText().toString().isEmpty() && !txt_otp2.getText().toString().isEmpty()
                        && !txt_otp3.getText().toString().isEmpty() && !txt_otp4.getText().toString().isEmpty()
                        && !txt_otp5.getText().toString().isEmpty() && !txt_otp6.getText().toString().isEmpty()) {
                    String otp = txt_otp1.getText().toString() + txt_otp2.getText().toString().trim()
                            + txt_otp3.getText().toString() + txt_otp4.getText().toString().trim()
                            + txt_otp5.getText().toString() + txt_otp6.getText().toString().trim();
                    if (getbackend != null) {
                        btn_pb2.setVisibility(View.VISIBLE);
                        btn_verifyotp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(getbackend, otp);
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        btn_pb2.setVisibility(View.GONE);
                                        btn_verifyotp.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {

                                            DocumentReference docref = fstore.collection("users").document(fauth.getCurrentUser().getUid());
                                            docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    if (documentSnapshot.exists()) {
                                                        Intent cu = new Intent(getApplicationContext(), Dashboard.class);
                                                        startActivity(cu);
                                                        finish();
                                                    } else {
                                                        Intent eu = new Intent(getApplicationContext(), User_Profile.class);
                                                        eu.putExtra("mobile",Mobile);
                                                        startActivity(eu);
                                                        finish();
                                                    }
                                                }
                                            });
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(getApplicationContext(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(getApplicationContext(), "OTP Verify", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter 6 Digits OTP", Toast.LENGTH_SHORT).show();

                }
            }
        });
        otpmove();
    }

    private void otpmove() {

        txt_otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txt_otp1.getText().toString().trim().isEmpty()) {
                    txt_otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txt_otp2.getText().toString().trim().isEmpty()) {
                    txt_otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txt_otp3.getText().toString().trim().isEmpty()) {
                    txt_otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txt_otp4.getText().toString().trim().isEmpty()) {
                    txt_otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txt_otp5.getText().toString().trim().isEmpty()) {
                    txt_otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}