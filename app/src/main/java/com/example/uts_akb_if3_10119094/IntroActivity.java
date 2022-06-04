package com.example.uts_akb_if3_10119094;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPager introViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        //fill  list screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Selamat Datang Di Noter", "Mari mencatat semua hal penting dalam hidup kamu di Notas!",R.drawable.imgnote3));
        mList.add(new ScreenItem("Jangan sampai lupa", "Catat kegiatan penting mu, agar kamu tidak lupa",R.drawable.imgnote2));
        mList.add(new ScreenItem("Jadwal nanti", "Catat kegiatan selanjutnya untuk meningkatkan produktifitasmu",R.drawable.imgnote1));

        //setup viewpager
        screenPager = findViewById(R.id.ScreenViewPager);
        introViewPager = new IntroViewPager(this,mList);
        screenPager.setAdapter(introViewPager);

        Button btnnext = findViewById(R.id.nextbtn);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}