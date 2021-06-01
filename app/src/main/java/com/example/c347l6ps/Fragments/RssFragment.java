package com.example.c347l6ps.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crazyhitty.chdev.ks.rssmanager.Channel;
import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;
import com.example.c347l6ps.FeedArrayAdapter;
import com.example.c347l6ps.R;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RssFragment extends Fragment implements RssReader.RssCallback, View.OnClickListener {

    private static final String TAG = RssFragment.class.getSimpleName();
    private static final String RSS_CNN_URL = "https://www.channelnewsasia.com/rssfeeds/8395986";
    private static final String RSS_SINGSTAT_URL = "https://www.singstat.gov.sg/rss";

    private RssReader rssReader = new RssReader(this);

    // List View Components
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<Channel.Item> feedArray;

    private AppCompatImageButton reloadFeedButton;

    public RssFragment() {
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
        View view = inflater.inflate(R.layout.fragment_rss, container, false);
        listView = view.findViewById(R.id.cnn_feed_list_view);
        reloadFeedButton = view.findViewById(R.id.reload_feed_image_button);
        reloadFeedButton.setVisibility(View.GONE);
        reloadFeedButton.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        feedArray = new ArrayList<>();
        arrayAdapter = new FeedArrayAdapter(getContext(), R.layout.list_feed_item, feedArray);
        listView.setAdapter(arrayAdapter);

        loadRSSFeed();
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        // Log content of RSS List
        StringBuilder sb = new StringBuilder();
        for (RSS rss: rssList) {
            sb.append(rss.toString() + "\n");
        }
        Log.d(TAG, sb.toString());

        feedArray.clear();

        // Channels
        for (RSS rss: rssList) {
            List<Channel.Item> channelItems = rss.getChannel().getItems();
            // Add Items of Channels to Items Array
            feedArray.addAll(channelItems);
        }

        // Shuffle Items
        Collections.shuffle(feedArray);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {
        Log.e(TAG + " > unableToReadRssFeeds", errorMessage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        loadRSSFeed();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    private void loadRSSFeed() {
        if (!isNetworkConnected()) {
            Snackbar.make(getView(), "No Internet Connection", Snackbar.LENGTH_SHORT).show();
            reloadFeedButton.setVisibility(View.VISIBLE);
        } else {
            reloadFeedButton.setVisibility(View.GONE);
            rssReader.loadFeeds(RSS_CNN_URL, RSS_SINGSTAT_URL);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onClick(View v) {
        loadRSSFeed();
    }
}