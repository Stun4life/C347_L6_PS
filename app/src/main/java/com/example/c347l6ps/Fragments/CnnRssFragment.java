package com.example.c347l6ps.Fragments;

import android.os.Bundle;

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

import java.util.List;

public class CnnRssFragment extends Fragment implements RssReader.RssCallback{

    private static final String TAG = CnnRssFragment.class.getSimpleName();
    private static final String RSS_CNN_URL = "https://www.channelnewsasia.com/rssfeeds/8395986";

    private RssReader rssReader = new RssReader(this);

    // List View Components
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<Channel.Item> feedArray;


    public CnnRssFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rssReader.loadFeeds(RSS_CNN_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cnn_rss, container, false);
        listView = view.findViewById(R.id.cnn_feed_list_view);
        return view;
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        StringBuilder sb = new StringBuilder();
        for (RSS rss: rssList) {
            sb.append(rss.toString() + "\n");
        }
        Log.d(TAG, sb.toString());

        feedArray = rssList.get(0).getChannel().getItems();
        arrayAdapter = new FeedArrayAdapter(getContext(), R.layout.list_feed_item, feedArray);
        listView.setAdapter(arrayAdapter);
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
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
}