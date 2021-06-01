package com.example.c347l6ps;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Screen1 extends Fragment {

    Button btnChangeColour;

    ArrayList<String> factArrayList = new ArrayList<String>();
    ListView firstListView;
    ArrayAdapter arrayAdapter;

    public Screen1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen1, container, false);
        firstListView = view.findViewById(R.id.listview1);
        btnChangeColour = view.findViewById(R.id.btnChangeColour);
        btnChangeColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstListView.setBackgroundColor(Color.CYAN);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //add items
        factArrayList.add("Pound for pound, your bones are stronger than steel.\n" +
                "            A block of bone the size of a matchbox can support up to 18,000 pounds of weight");
        factArrayList.add("Test123");
        factArrayList.add("Test123456");

        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, factArrayList);

        firstListView.setAdapter(arrayAdapter);

        Log.i("info", String.valueOf(factArrayList));

    }
}