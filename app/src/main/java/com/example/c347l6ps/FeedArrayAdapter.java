package com.example.c347l6ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyhitty.chdev.ks.rssmanager.Channel;
import java.util.List;

public class FeedArrayAdapter extends ArrayAdapter<Channel.Item> {

    private List<Channel.Item> feeds;
    private Context context;
    private int resource;

    public FeedArrayAdapter(@NonNull Context context, int resource,
                            @NonNull List<Channel.Item> feeds) {
        super(context, resource, feeds);
        this.feeds = feeds;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        // Get Data
        Channel.Item feed = feeds.get(position);

        // Set Data of View
        TextView feedTitleTV, feedDescTV, feedCatTV, feedPubDateTV;
        feedTitleTV = view.findViewById(R.id.feed_title_text_view);
        feedDescTV = view.findViewById(R.id.feed_desc_text_view);
        feedCatTV = view.findViewById(R.id.feed_category_text_view);
        feedPubDateTV = view.findViewById(R.id.feed_pub_date_text_view);

        feedTitleTV.setText(feed.getTitle());
        feedDescTV.setText(feed.getDescription());
        feedCatTV.setText(feed.getCategory());
        feedPubDateTV.setText(feed.getPubDate());

        return view;
    }
}
