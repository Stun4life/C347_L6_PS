package com.example.c347l6ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.c347l6ps.Fragments.CnnRssFragment;
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


    private ImageView ivTrivia;
    // Shared preferences for screen state
    private String activeFrag;

    public static final String SHARED_SCREEN = "lastActiveScreen";
    private Button btnReadLater;

    FragmentManager fm;

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
        al.add(new CnnRssFragment());

        loadSharedPrefs();
        updateFragmentOnStart();

        adapter = new MyFragmentPageAdapter(fm, al);
        viewPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPrefs();
            }
        });

        Glide.with(this).load("https://otogi.wikiru.jp/attach2/A1FA352FA5CFA5E0A5ECA5C3A5C8_53445FA5CFA5E0A5ECA5C3A5C8305F766963746F72792E676966.gif").into(ivTrivia);

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
                int random = new Random().nextInt(2);
                Log.i("numbers", String.valueOf(random));
                if (random == 1) {
                    int max2 = viewPager.getChildCount();
                    if (viewPager.getCurrentItem() < max2 - 1) {
                        int nextPage = viewPager.getCurrentItem() + 1;
                        viewPager.setCurrentItem(nextPage, true);
                        return true;

                    }
                } else if (random == 2) {
                    if (viewPager.getCurrentItem() > 0) {
                        int previousPage = viewPager.getCurrentItem() - 1;
                        viewPager.setCurrentItem(previousPage, true);
                        return true;
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void saveSharedPrefs(){
        setCurrentFragment();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_SCREEN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_SCREEN, activeFrag);

        editor.apply();
    }

    public void loadSharedPrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_SCREEN, MODE_PRIVATE);

        activeFrag = sharedPreferences.getString(SHARED_SCREEN, "");
    }

    public void updateFragmentOnStart(){
        if(activeFrag.equalsIgnoreCase("frag1")){
            viewPager.setCurrentItem(1, true);
        }else{
            viewPager.setCurrentItem(2, true);
        }
    }

    public void setCurrentFragment() {
        Fragment currentFrag = fm.findFragmentById(R.id.container);
        if(currentFrag == al.get(0)){
            activeFrag = "frag1";
        }else{
            activeFrag = "frag2";
        }
    }
}