package com.example.c347l6ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPageAdapter adapter;
    ViewPager viewPager;

//    String[] facts1;
//    String[] facts2;
//
//    ArrayAdapter aa;
//    ArrayList<String[]> factsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        FragmentManager fm = getSupportFragmentManager();
        Resources res = getResources();

//        facts1 = res.getStringArray(R.array.facts);
//        facts2 = res.getStringArray(R.array.facts2);
//
//        factsArray = new ArrayList<>();
//        factsArray.add(facts1);
//        factsArray.add(facts2);

        al = new ArrayList<>();
        al.add(new Screen1());
        al.add(new Screen2());

        adapter = new MyFragmentPageAdapter(fm, al);
        viewPager.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Next:
                int max = viewPager.getChildCount();
                if (viewPager.getCurrentItem() < max - 1) {
                    int nextPage = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(nextPage, true);
                    return true;

                }
            case R.id.action_previous:
                if (viewPager.getCurrentItem() > 0) {
                    int previousPage = viewPager.getCurrentItem() - 1;
                    viewPager.setCurrentItem(previousPage, true);
                    return true;

                }
            case R.id.action_Random:
                int random = new Random().nextInt(3);
                Log.i("numbers", String.valueOf(random));
                if (random == 1) {
                    int max2 = viewPager.getChildCount();
                    if (viewPager.getCurrentItem() < max2 -1) {
                        int nextPage = viewPager.getCurrentItem() + 1;
                        viewPager.setCurrentItem(nextPage, true);
                        return true;

                    }
                }
                else if (random == 2){
                    if (viewPager.getCurrentItem() > 0) {
                        int previousPage = viewPager.getCurrentItem() - 1;
                        viewPager.setCurrentItem(previousPage, true);
                        return true;
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }
        }}