package rejasupotaro.rebuild.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rejasupotaro.rebuild.R;
import rejasupotaro.rebuild.models.Tweet;
import rejasupotaro.rebuild.utils.PicassoHelper;
import rejasupotaro.rebuild.utils.ViewUtils;

public class TweetListAdapter extends BindableAdapter<Tweet> {

    public TweetListAdapter(Context context) {
        this(context, new ArrayList<Tweet>());
    }

    public TweetListAdapter(Context context,
            List<Tweet> episodeList) {
        super(context, episodeList);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.list_item_tweet, null);
    }

    @Override
    public void bindView(Tweet item, int position, View view) {
        ImageView userProfileImageView = (ImageView) view.findViewById(R.id.user_profile_image);
        PicassoHelper.load(getContext(), userProfileImageView, item.getUserImageUrl());

        TextView userNameTextView = (TextView) view.findViewById(R.id.user_name_text);
        userNameTextView.setText(item.getUserName());

        TextView tweetTextView = (TextView) view.findViewById(R.id.tweet_text);
        ViewUtils.setTweetText(tweetTextView, item.getText());
    }
}
