package com.example.c347l6ps;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Screen1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Screen1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button btnChangeColour;

    ArrayList<String> factArrayList = new ArrayList<String>(){
    };
    ListView l1;
    ListView firstListView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Screen1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Screen1.
     */
    // TODO: Rename and change types and number of parameters
    public static Screen1 newInstance(String param1, String param2) {
        Screen1 fragment = new Screen1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen1, container, false);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_main, factArrayList);
        firstListView = view.findViewById(R.id.listview1);
        factArrayList.add("Pound for pound, your bones are stronger than steel.\n" +
                "            A block of bone the size of a matchbox can support up to 18,000 pounds of weight");
        factArrayList.add("Test123");
        factArrayList.add("Test123456");
        Log.i("info", String.valueOf(factArrayList));
        firstListView.setAdapter(arrayAdapter);
        btnChangeColour = view.findViewById(R.id.btnChangeColour);
        l1 = view.findViewById(R.id.listview1);
        btnChangeColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundColor(Color.CYAN);
            }
        });
        return view;
    }
}