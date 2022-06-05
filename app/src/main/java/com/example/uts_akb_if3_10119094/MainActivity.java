
//10119094 IF-3 Saeful Anwar Oktariansah

package com.example.uts_akb_if3_10119094;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.uts_akb_if3_10119094.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListView listView;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FirstFragment());

//        FirstFragment fragment = new FirstFragment();
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.firstFragment);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    replaceFragment(new FirstFragment());
                    break;
                case R.id.secondFragment:
                    replaceFragment(new SecondFragment());
                    break;
                case R.id.thirdFragment:
                        replaceFragment(new ThirdFragment());
                    break;
            }
            return true;
        });
    }
//    public void setListView(){
//        Cursor cursor = helper.allData();
//        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
//        listView.setAdapter(customCursorAdapter);
//    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();
    }
}