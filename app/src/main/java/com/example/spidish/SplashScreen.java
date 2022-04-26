package com.example.spidish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.spidish.fragments.HomeFragment;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView spi,dish;
    SQLiteDatabase db;
    MyHelper ob;

    private static int SPLASH_SCREEN = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        spi=findViewById(R.id.spi);
        dish=findViewById(R.id.dish);
        lottieAnimationView = findViewById(R.id.lottie);
        ob = new MyHelper(getApplicationContext());

        ob=new MyHelper(getApplicationContext());
        lottieAnimationView.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        spi.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        dish.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                        db=ob.getReadableDatabase();
                        Cursor cr = db.rawQuery("select * from login", null);
                    if(cr.moveToNext())

                        {
                            Intent obi = new Intent(getApplicationContext(), Dashboard.class);
                            obi.putExtra("mobile", cr.getString(0));
                            startActivity(obi);
                            finish();
                        }
                    else

                        {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
        }
    },SPLASH_SCREEN);
}
}