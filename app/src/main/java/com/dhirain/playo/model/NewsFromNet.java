package com.dhirain.playo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by DJ on 11-10-2017.
 */

public class NewsFromNet {

    @SerializedName("hits")
    @Expose
    private ArrayList<News> hits;

    public ArrayList<News> getHits() {
        return hits;
    }
}
