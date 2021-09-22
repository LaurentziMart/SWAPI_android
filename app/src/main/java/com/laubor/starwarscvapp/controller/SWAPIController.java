package com.laubor.starwarscvapp.controller;

import android.annotation.SuppressLint;

import com.laubor.starwarscvapp.endpoint.SWAPIService;
import com.laubor.starwarscvapp.model.Character;
import com.laubor.starwarscvapp.model.People;
import com.laubor.starwarscvapp.model.Planet;
import com.laubor.starwarscvapp.model.Species;
import com.laubor.starwarscvapp.model.Starship;
import com.laubor.starwarscvapp.model.Vehicle;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SWAPIController extends Controller {
    public static String TAG = "SWAPIController";

    @SuppressLint("CheckResult")
    public static void getCharacter(Retrofit retrofit, String url, CharacterCallback callback){
        SWAPIService service =retrofit.create(SWAPIService.class);
        Observable<Character> people= service.getCharacter(url);
        Observable<List<Vehicle>> vehicleObservables= people.flatMap(character -> getVehicleObservables(service,character));
        Observable<List<Starship>>starshipObservables= people.flatMap(character -> getStarshipObservables(service,character));
        Observable<List<Species>>speciesObservables= people.flatMap(character -> getSpeciesObservables(service,character));
        Observable<Planet> homeworldObservable= people.flatMap(character -> service.getHomeworld(character.getHomeworld()));
        Observable.zip(people,vehicleObservables,new BiFunction<Character, List<Vehicle>, Character>() {
                    @Override
                    public Character apply(@NonNull Character character, @NonNull List<Vehicle> vehicles) throws Exception {
                        character.setVehicleList(vehicles);
                        return character;
                    }
                }

        )
                .zipWith(starshipObservables,new BiFunction<Character, List<Starship>, Character>() {
                    @Override
                    public Character apply(@NonNull Character character, @NonNull List<Starship> starships) throws Exception {
                        character.setStarshipList(starships);
                        return character;
                    }
                })
                .zipWith(speciesObservables,new BiFunction<Character, List<Species>, Character>() {
                    @Override
                    public Character apply(@NonNull Character character, @NonNull List<Species> species) throws Exception {
                        character.setSpeciesList(species);
                        return character;
                    }
                })
                .zipWith(homeworldObservable,new BiFunction<Character, Planet, Character>() {
                    @Override
                    public Character apply(@NonNull Character character, @NonNull Planet planet) throws Exception {
                        character.setPlanet(planet);
                        return character;
                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError((throwable)-> callback.onError(throwable.getCause()))
                .subscribe(character -> callback.onSuccess(character));

    }
    public static Observable<Character> getCharacterObservable(Retrofit retrofit, String url){
        SWAPIService service =retrofit.create(SWAPIService.class);
        Observable<Character> people= service.getCharacter(url);
        Observable<List<Vehicle>> vehicleObservables= people.flatMap(character -> getVehicleObservables(service,character));
        Observable<List<Starship>>starshipObservables= people.flatMap(character -> getStarshipObservables(service,character));
        return Observable.zip(people,vehicleObservables,new BiFunction<Character, List<Vehicle>, Character>() {
                    @Override
                    public Character apply(@NonNull Character character, @NonNull List<Vehicle> vehicles) throws Exception {
                        character.setVehicleList(vehicles);
                        return character;
                    }
                }

        )
                .zipWith(starshipObservables,new BiFunction<Character, List<Starship>, Character>() {
                    @Override
                    public Character apply(@NonNull Character character, @NonNull List<Starship> starships) throws Exception {
                        character.setStarshipList(starships);
                        return character;
                    }
                })
                ;


    }

    private static Observable<List<Vehicle>> getVehicleObservables(SWAPIService service, Character character) {
        return Observable.fromIterable(character.vehicles)
                .flatMap(vehicle -> getVehicleObservable(service,vehicle)).toList().toObservable();

    }
    private static Observable<List<Species>> getSpeciesObservables(SWAPIService service, Character character) {
        return Observable.fromIterable(character.species)
                .flatMap(specie -> getSpecieObservable(service,  specie)).toList().toObservable();

    }

    private static ObservableSource<Species> getSpecieObservable(SWAPIService service, String specieUrl) {
        return service.getSpecies(specieUrl);

    }

    public static Observable<Vehicle> getVehicleObservable(SWAPIService service, String vehicleUrl){
        return service.getVehicles(vehicleUrl);

    }
    private static Observable<List<Starship>> getStarshipObservables(SWAPIService service, Character character) {
        return Observable.fromIterable(character.starships)
                .flatMap(starship -> getStarshipObservable(service,starship)).toList().toObservable();

    }


    public static Observable<Starship> getStarshipObservable(SWAPIService service, String starshipUrl){
        return service.getStarship(starshipUrl);

    }
    public static void getPeople(Retrofit retrofit,int pagination,PeopleCallback callback){
        SWAPIService service =retrofit.create(SWAPIService.class);
        Observable<People> people= service.getAllPeople(pagination);
        people  .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError((throwable)-> callback.onError(throwable.getCause()))
                .subscribe(peopleList -> callback.onSuccess(peopleList),
                        throwable -> callback.onError(throwable));

    }
    public static void searchPeople(Retrofit retrofit,int pagination,String searchQuery,PeopleCallback callback){
        SWAPIService service =retrofit.create(SWAPIService.class);
        Observable<People> people= service.searchPeople(searchQuery,pagination);
        people  .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError((throwable)-> callback.onError(throwable.getCause()))
                .subscribe(peopleList -> callback.onSuccess(peopleList),
                        throwable -> callback.onError(throwable));

    }

    public interface PeopleCallback{
        void onSuccess(People people);
        void onError(Throwable error);
    }
    public interface CharacterCallback{
        void onSuccess(Character character);
        void onError(Throwable error);
    }

    public interface VehiclesCallback{
        void onSuccess(List<Vehicle> vehicles);
        void onError(Throwable error);
    }

}
