package com.dhirain.playo.UI.homeSreen.presenter;

import android.content.Context;
import android.util.Log;

import com.dhirain.playo.UI.homeSreen.view.HomeScreenView;
import com.dhirain.playo.model.News;
import com.dhirain.playo.model.NewsFromNet;
import com.dhirain.playo.network.NewsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DJ on 11-10-2017.
 */

public class HomeScreenPresenter {


    private static final String TAG = "HomeScreenPresenter";
    private HomeScreenView homeScreenView;
    private Context context;
    private List<News> totalNewsList;
    private List<News> currentNewsList;
    private int currentPage = 0;

    public HomeScreenPresenter(HomeScreenView homeScreenView, Context context) {
        this.homeScreenView = homeScreenView;
        this.context = context;


    }

    public void onViewAttached() {
        homeScreenView.showProgress();

            Log.d(TAG, "onViewAttached: from network");
            fetchRepoFromNetworkOrCache("");


    }


    //fetch List of repo  by using retrofit
    private void fetchRepoFromNetworkOrCache(String query) {
        Call<NewsFromNet> repos=NewsService.instance().getAllNews(query);
        repos.enqueue(new Callback<NewsFromNet>() {
            @Override
            public void onResponse(Call<NewsFromNet> call, Response<NewsFromNet> response) {
                Log.d(TAG, "onResponse: "+call.toString());
                Log.d(TAG, "onResponse: "+response.toString());
                if (response.isSuccessful()) {
                    NewsFromNet fromNet = response.body();
                    totalNewsList = fromNet.getHits();
                    paggination();
                    homeScreenView.hideProgress();

                }
            }

            @Override
            public void onFailure(Call<NewsFromNet> call, Throwable t) {
                t.printStackTrace();
                homeScreenView.hideProgress();
                homeScreenView.showNetworkFail();
            }
        });
    }

    public void paggination() {
        //sortByTime(isDescending);
        if (currentNewsList == null) {
            currentPage = 0;
            currentNewsList = new ArrayList<>();
        }
        int tillPage = currentPage + 20;
        while (currentPage < tillPage && currentPage < totalNewsList.size()) {
            currentNewsList.add(totalNewsList.get(currentPage));
            currentPage++;
        }
        Log.d(TAG, "paggination: " + currentPage);
        homeScreenView.updateList(currentNewsList);
        /*currentNewsList = totalNewsList;
        homeScreenView.updateList(totalNewsList);*/
    }

    public void search(String query) {
        homeScreenView.showProgress();
        query = query.toLowerCase();
        currentPage = 0;
        currentNewsList = null;
        totalNewsList = null;
        fetchRepoFromNetworkOrCache(query);

    }


    /*public void sortByTime(boolean descending) {
        if(descending)
        Collections.sort(totalNewsList, new DescendingDateComparator());
        else
            Collections.sort(totalNewsList, new AscendingDateComparator());
    }*/



}
