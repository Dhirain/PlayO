package com.dhirain.playo.UI.homeSreen.view;


import com.dhirain.playo.model.News;

import java.util.List;

/**
 * Created by DJ on 11-10-2017.
 */

public interface HomeScreenView {

    void showProgress();

    void hideProgress();

    void showNetworkFail();

    void updateList(List<News> totalNewsList);

    void showFilterScreen();

    void showSnackMessage(String s);

    void showEmptyFilterState();

    void showListState();
}
