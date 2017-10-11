package com.dhirain.playo.UI.homeSreen.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhirain.playo.R;


/**
 * Created by DJ on 11-10-2017.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView titleText,url,timestamp,author,noComments,points;
    public ImageView like;
    public LinearLayout meta;
    public RelativeLayout parent;
    public CardView cardView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.projectName);
        url = (TextView) itemView.findViewById(R.id.url_text);
        timestamp = (TextView) itemView.findViewById(R.id.days_text);
        author = (TextView) itemView.findViewById(R.id.author_text);
        noComments = (TextView) itemView.findViewById(R.id.comments_text);
        points = (TextView) itemView.findViewById(R.id.points_text);
        parent = (RelativeLayout) itemView.findViewById(R.id.parentRR);
        meta = (LinearLayout) itemView.findViewById(R.id.meta_detail);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
    }
}
