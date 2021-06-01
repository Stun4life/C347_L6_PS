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

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPageAdapter adapter;
    ViewPager viewPager;
    ListView firstListView, secondListView;

    LinearLayout l1, l2;

    String[] facts1;
    String[] facts2;
    ArrayAdapter aa;

    ArrayList<String[]> factsArray;

    ArrayList<String> factArrayList = new ArrayList<String>(){
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        firstListView = findViewById(R.id.listview1);
        secondListView = findViewById(R.id.listview2);
        l1 = findViewById(R.id.firstLinear);
        l2 = findViewById(R.id.secondLinear);

        FragmentManager fm = getSupportFragmentManager();
        Resources res = getResources();

        facts1 = res.getStringArray(R.array.facts);
        facts2 = res.getStringArray(R.array.facts2);

        factsArray = new ArrayList<>();
        factsArray.add(facts1);
        factsArray.add(facts2);

        al = new ArrayList<Fragment>();
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
            //case R.id.action_Random:
            default:
                return super.onOptionsItemSelected(item);
        }
        }}