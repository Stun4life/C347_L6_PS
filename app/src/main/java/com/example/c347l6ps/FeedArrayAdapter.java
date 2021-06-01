package com.example.c347l6ps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyhitty.chdev.ks.rssmanager.Channel;

import java.time.LocalDateTime;
import java.util.List;

public class FeedArrayAdapter extends ArrayAdapter<Channel.Item> {

    private static final String TAG = FeedArrayAdapter.class.getSimpleName();

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
        TextView feedTitleTV, feedDescTV, feedCatTV, feedPubDateTV, feedChannelTV;
        feedTitleTV = view.findViewById(R.id.feed_title_text_view);
        feedDescTV = view.findViewById(R.id.feed_desc_text_view);
        feedCatTV = view.findViewById(R.id.feed_category_text_view);
        feedPubDateTV = view.findViewById(R.id.feed_pub_date_text_view);
        feedChannelTV = view.findViewById(R.id.feed_channel_text_view);

        // -> Title, Description
        feedTitleTV.setText(feed.getTitle());
        feedDescTV.setText(feed.getDescription());

        // -> Category
        String category = feed.getCategory();
        if (category == null) {
            feedCatTV.setText("");
        } else {
            feedCatTV.setText(category);
        }

        // -> Publication Date
        String pubDate = feed.getPubDate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
            String month = dateTime.getMonth() + "";
            month = month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase(); // capatilize first letter
            String day = dateTime.getDayOfMonth() + "";
            String year = dateTime.getYear() + "";
            pubDate = String.format("%s %s %s", day, month, year);
        }
        feedPubDateTV.setText(pubDate);

        // -> Channel
        if (feed.getLink().startsWith("https://www.channelnewsasia.com")) {
            feedChannelTV.setText("CNA");
        } else {
            feedChannelTV.setText("SingStat");
        }

        return view;
    }
}
