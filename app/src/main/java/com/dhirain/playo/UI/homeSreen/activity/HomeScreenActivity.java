package com.dhirain.playo.UI.homeSreen.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dhirain.playo.R;
import com.dhirain.playo.UI.homeSreen.fragment.ListNewsFragment;


public class HomeScreenActivity extends AppCompatActivity {
    public static final int FILTER_REQUEST_CODE = 101;
    private ListNewsFragment listNewsFragment;
    //FloatingActionButton Filterfab;
    private boolean isFavChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        setUI();

        showFragment();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home_screen, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    listNewsFragment.search(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //Give query to presenter
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showFragment() {
        listNewsFragment = ListNewsFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.listContainer, listNewsFragment);
        transaction.commit();
    }

}
