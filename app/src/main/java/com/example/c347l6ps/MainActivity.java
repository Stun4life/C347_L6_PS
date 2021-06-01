package com.example.c347l6ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.c347l6ps.Fragments.RssFragment;
import com.example.c347l6ps.Fragments.Fact1Fragment;
import com.example.c347l6ps.Fragments.Fact2Fragment;

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
    FragmentManager fm;

    // Shared preferences for screen state
    private int currentFrag;

    public static final int SHARED_SCREEN = 1;
    private Button btnReadLater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadLater = findViewById(R.id.btnReadLater);
        viewPager = findViewById(R.id.viewPager);
        fm = getSupportFragmentManager();
        Resources res = getResources();


//        facts1 = res.getStringArray(R.array.facts);
//        facts2 = res.getStringArray(R.array.facts2);
//
//        factsArray = new ArrayList<>();
//        factsArray.add(facts1);
//        factsArray.add(facts2);

        al = new ArrayList<>();
        al.add(new Fact1Fragment());
        al.add(new Fact2Fragment());
        al.add(new RssFragment());

        loadSharedPrefs();

        adapter = new MyFragmentPageAdapter(fm, al);
        viewPager.setAdapter(adapter);
        Log.e("READ LATER", String.valueOf(currentFrag));

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPrefs();
            }
        });
        updateFragmentOnStart();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
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
                int random = new Random().nextInt(al.size());
                Log.i("numbers", String.valueOf(random));
                viewPager.setCurrentItem(random, true);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveSharedPrefs(){
        setCurrentFragment();

        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(SHARED_SCREEN), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(String.valueOf(SHARED_SCREEN), currentFrag);

        editor.apply();
    }

    public void loadSharedPrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(SHARED_SCREEN), MODE_PRIVATE);

        currentFrag = sharedPreferences.getInt(String.valueOf(SHARED_SCREEN), 0);
    }

    public void updateFragmentOnStart(){
        if(currentFrag == 0){
            viewPager.setCurrentItem(0, true);
        }else if(currentFrag == 1){
            viewPager.setCurrentItem(1, true);
        }else{
            viewPager.setCurrentItem(2, true);
        }
    }

    public void setCurrentFragment() {
        currentFrag = viewPager.getCurrentItem();
        Log.e("FRAG", String.valueOf(currentFrag));
    }
}


