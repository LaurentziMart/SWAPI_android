package com.laubor.starwarscvapp.endpoint;

import com.laubor.starwarscvapp.model.Character;
import com.laubor.starwarscvapp.model.People;
import com.laubor.starwarscvapp.model.Planet;
import com.laubor.starwarscvapp.model.Species;
import com.laubor.starwarscvapp.model.Starship;
import com.laubor.starwarscvapp.model.Vehicle;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SWAPIService {

    @GET("people/")
    public Observable<People> getAllPeople(@Query("page") int pagination);
    @GET("people/")
    public Observable<People> searchPeople(@Query("search") String searchQuery,@Query("page") int pagination);
    @GET
    public Observable<Character> getCharacter(@Url String url);

    @GET
    public Observable<Species> getSpecies(@Url String url);

    @GET
    public Observable<Starship> getStarship(@Url String url);

    @GET
    public Observable<Vehicle> getVehicles(@Url String url);
    @GET
    public Observable<Planet> getHomeworld(@Url String url);

}
