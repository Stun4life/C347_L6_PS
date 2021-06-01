package com.example.c347l6ps.Fragments;

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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.c347l6ps.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fact2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fact2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button btnChangeColour2;

    ArrayList<String> factArrayList = new ArrayList<String>();
    ListView secondListView2;
    ArrayAdapter arrayAdapter;

    LinearLayout l2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fact2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fact2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fact2Fragment newInstance(String param1, String param2) {
        Fact2Fragment fragment = new Fact2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_fact_2, container, false);
        btnChangeColour2 = view.findViewById(R.id.btnChangeColour2);
        secondListView2 = view.findViewById(R.id.listview2);
        btnChangeColour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(6);
                if (random == 1) {
                    secondListView2.setBackgroundColor(Color.CYAN);
                }
                else if (random == 2) {
                    secondListView2.setBackgroundColor(Color.RED);
                }
                else if (random == 3) {
                    secondListView2.setBackgroundColor(Color.YELLOW);
                }
                else if (random == 4) {
                    secondListView2.setBackgroundColor(Color.GREEN);
                }
                else if (random == 5) {
                    secondListView2.setBackgroundColor(Color.GRAY);
                }
                else {
                    secondListView2.setBackgroundColor(Color.BLUE);
                }
            }
        });
        return view;

    }
    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //add items
        factArrayList.add("Despite accounting for 2% of our body mass, the brain uses 20% of our oxygen and blood supply");
        factArrayList.add("Teeth are considered part of the skeletal system, but are not counted as bones");
        factArrayList.add("The largest bone in the human body is the femur, also known as the thigh bone. The smallest bone is the stirrup bone, which is located inside your ear drum");

        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, factArrayList);

        secondListView2.setAdapter(arrayAdapter);

        Log.i("info", String.valueOf(factArrayList));

    }
}