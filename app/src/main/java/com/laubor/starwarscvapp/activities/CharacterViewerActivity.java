package com.laubor.starwarscvapp.activities;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.activities.abstracts.BaseActivity;
import com.laubor.starwarscvapp.activities.helper.StarshipsRecyclerViewAdapter;
import com.laubor.starwarscvapp.activities.helper.VehiclesRecyclerViewAdapter;
import com.laubor.starwarscvapp.controller.SWAPIController;
import com.laubor.starwarscvapp.model.Character;
import com.laubor.starwarscvapp.model.Species;
import com.laubor.starwarscvapp.model.Starship;
import com.laubor.starwarscvapp.model.Vehicle;
import com.laubor.starwarscvapp.settings.Literals;
import com.laubor.starwarscvapp.utils.TransportDialog;

import java.util.Optional;

import butterknife.BindView;

public class CharacterViewerActivity extends BaseActivity implements StarshipsRecyclerViewAdapter.ItemClickListener, VehiclesRecyclerViewAdapter.ItemClickListener{
     String characterUrl;
    @BindView(R.id.swipeRefreshVehicles)
     SwipeRefreshLayout swipeRefreshLayoutVehicles;
    @BindView(R.id.swipeRefreshStarships)
     SwipeRefreshLayout swipeRefreshLayoutStarships;
    @BindView(R.id.recyclerViewVehicles)
     RecyclerView rvVehicles;
    @BindView(R.id.recyclerViewStarships)
     RecyclerView rvStarships;
    private VehiclesRecyclerViewAdapter adapterVehicles;
    private StarshipsRecyclerViewAdapter adapterStarships;

    @BindView(R.id.tvCharacterName)
     TextView tvCharacterName;
    @BindView(R.id.edHeight)
     EditText edHeight;
    @BindView(R.id.edMass)
     EditText edMass;
    @BindView(R.id.edHair)
     EditText edHair;
    @BindView(R.id.edEyes)
     EditText edEyes;
    @BindView(R.id.edGender)
     EditText edGender;
    @BindView(R.id.edSkin)
     EditText edSkin;
    @BindView(R.id.edHomeWorld)
     EditText edHomeWorld;
    @BindView(R.id.edSpecies)
     EditText edSpecies;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.cardView)
    CardView cardView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config(getIntent().getExtras());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_character_viewer;
    }
    private void config(Bundle extras) {
        progressBar.setIndeterminate(true);
        characterUrl=extras.getString(Literals.EXTRA_CHARACTER_URL);
        configRecyclerViews();
        loadCharacter();


    }

    private void configRecyclerViews() {

        rvVehicles.setLayoutManager(new LinearLayoutManager(this));
        rvStarships.setLayoutManager(new LinearLayoutManager(this));
        configSwipeRefreshAdapters();

    }

    private void configSwipeRefreshAdapters() {
        swipeRefreshLayoutVehicles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCharacter();
            }
        });
        swipeRefreshLayoutStarships.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCharacter();
            }
        });
    }

    private void loadCharacter() {
        client.getCharacter(characterUrl, new SWAPIController.CharacterCallback() {
            @Override
            public void onSuccess(Character character) {
                setCardViewVisible();
                fillAdapters(character);
                fillUI(character);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    private void setCardViewVisible() {
        cardView.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
    }

    private void fillUI(Character character) {
        tvCharacterName.setText(getString(R.string.character_name,character.getName()));
        edHeight.setText(character.getHeight());
        edMass.setText(character.getMass());
        edHair.setText(character.getHair_color());
        edEyes.setText(character.getEye_color());
        edGender.setText(character.getGender());
        edSkin.setText(character.getSkin_color());
        edHomeWorld.setText(getCharacterPlanet(character));
        String specieName=getCharacterSpecieName(character);

        edSpecies.setText(specieName);
    }

    private String getCharacterPlanet(Character character) {
        String characterPlanet= getString(R.string.n_a);
        if(character.getPlanet()!=null && character.getPlanet().getName()!=null && character.getPlanet().getName().length()>0)characterPlanet=character.getPlanet().getName();
        return characterPlanet;
    }

    private String getCharacterSpecieName(Character character) {

        String characterSpecieName=getString(R.string.n_a);
        Optional<Species> result =character.getSpeciesList().stream().findFirst();
        if(result.isPresent())characterSpecieName=result.get().getName();

        return characterSpecieName;
    }

    private void fillAdapters(Character character) {
        swipeRefreshLayoutVehicles.setRefreshing(false);
        swipeRefreshLayoutStarships.setRefreshing(false);
        configRecyclerViews();
        adapterVehicles = new VehiclesRecyclerViewAdapter(getApplicationContext(), character.getVehicleList());
        adapterVehicles.setClickListener(CharacterViewerActivity.this::onVehicleClick);
        rvVehicles.setAdapter(adapterVehicles);
        adapterStarships = new StarshipsRecyclerViewAdapter(getApplicationContext(), character.getStarshipList());
        adapterStarships.setClickListener(CharacterViewerActivity.this::onStarshipClick);
        rvStarships.setAdapter(adapterStarships);
    }


    @Override
    public void onStarshipClick(View view, Starship starship) {
        TransportDialog dialog= new TransportDialog();
        dialog.showDialog(CharacterViewerActivity.this,starship);
    }

    @Override
    public void onVehicleClick(View view, Vehicle vehicle) {
        TransportDialog dialog= new TransportDialog();
        dialog.showDialog(CharacterViewerActivity.this,vehicle);
    }
}