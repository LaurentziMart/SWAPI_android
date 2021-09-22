package com.laubor.starwarscvapp.activities;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.activities.abstracts.BaseActivity;
import com.laubor.starwarscvapp.activities.helper.PaginationListener;
import com.laubor.starwarscvapp.activities.helper.SWAPIRecyclerAdapter;
import com.laubor.starwarscvapp.controller.SWAPIController;
import com.laubor.starwarscvapp.model.Character;
import com.laubor.starwarscvapp.model.People;
import com.laubor.starwarscvapp.settings.Literals;

import java.util.Random;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import java.util.ArrayList;

import static com.laubor.starwarscvapp.activities.helper.PaginationListener.PAGE_START;

public class MainActivity extends BaseActivity implements ShakeCallback, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener, MenuItem.OnMenuItemClickListener, SWAPIRecyclerAdapter.ItemClickListener {

    MediaPlayer soundPlayer;
    private static final String TAG=MainActivity.class.getCanonicalName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private SWAPIRecyclerAdapter adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        config();
        getSWPeople();
    }

    private void config() {
        initShakeConfig(this);

        configRecyclerView();
    }

    private void configRecyclerView() {
        swipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new SWAPIRecyclerAdapter(new ArrayList<>(),getApplicationContext(),MainActivity.this::onCharacterClick);
        mRecyclerView.setAdapter(adapter);


        mRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                getSWPeople();
            }
            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    public int getLayoutId(){
        return R.layout.activity_main;
    }

    private void getSWPeople() {
        swipeRefresh.setRefreshing(true);
        client.getPeople(currentPage,new SWAPIController.PeopleCallback() {
            @Override
            public void onSuccess(People people) {
                if (currentPage != PAGE_START) adapter.removeLoading();
                adapter.addItems(people.results);
                swipeRefresh.setRefreshing(false);

                if (currentPage < totalPage) {
                    adapter.addLoading();
                } else {
                    isLastPage = true;
                }
                isLoading = false;
            }

            @Override
            public void onError(Throwable error) {

            }
        });

    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        getSWPeople();
    }
    @Override
    public void onShake() {
        if(firstTimeShaking){
            firstTimeShaking=false;
            playSaberSound(SaberType.OPENING);
        }else{
            playSaberSound(SaberType.PLAYING);
        }
    }


    int sound = 0;
    private void playSaberSound(SaberType saberType) {
        switch (saberType){
            case OPENING:
                sound = R.raw.saber_open;
                break;
            case CLOSING:
                sound = R.raw.saber_close;
                break;
            case PLAYING:
                sound = getRandomSaberSoundID();
                break;


        }
        soundPlayer = MediaPlayer.create(MainActivity.this, sound);

        soundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });


    }

    private int getRandomSaberSoundID() {
        Random random= new Random();
        int[] saberSoundsIDs={R.raw.saber1,R.raw.saber2,R.raw.saber3,R.raw.saber4};

        return  saberSoundsIDs[random.nextInt(saberSoundsIDs.length)];
    }

    @Override
    protected void onDestroy() {
        playSaberSound(SaberType.CLOSING);
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem sortAlphabeticallyItem= menu.findItem(R.id.sortAlphabetically);
        MenuItem sortByFilmNumberItem= menu.findItem(R.id.sortByFimlNumber);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        sortAlphabeticallyItem.setOnMenuItemClickListener(this);
        sortByFilmNumberItem.setOnMenuItemClickListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        currentPage=1;
        client.searchPeople(query, currentPage, new SWAPIController.PeopleCallback() {
            @Override
            public void onSuccess(People people) {
                adapter.setItems(people.results);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        currentPage=1;
        client.searchPeople(newText, currentPage, new SWAPIController.PeopleCallback() {
            @Override
            public void onSuccess(People people) {
                adapter.setItems(people.results);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sortAlphabetically:
                adapter.sortCurrentItemsByType(SWAPIRecyclerAdapter.SortType.ALPHABETICALLY);
                break;
            case R.id.sortByFimlNumber:
                adapter.sortCurrentItemsByType(SWAPIRecyclerAdapter.SortType.BY_FILMS);

                break;
        }
        return false;
    }

    @Override
    public void onCharacterClick(View view, int position) {
        Character character=adapter.getItem(position);
        Intent characterViewerIntent = new Intent(MainActivity.this,CharacterViewerActivity.class);
        characterViewerIntent.putExtra(Literals.EXTRA_CHARACTER_URL,character.getUrl());
        startActivity(characterViewerIntent);
    }
}