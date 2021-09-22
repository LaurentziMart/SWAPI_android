package com.laubor.starwarscvapp;

import com.laubor.starwarscvapp.controller.SWAPIController;
import com.laubor.starwarscvapp.endpoint.SWAPIService;

public interface RestClient {

    void getCharacter(String characterUrl, SWAPIController.CharacterCallback callback);
    void getPeople(int pagination,SWAPIController.PeopleCallback callback);
    void searchPeople(String searchQuery, int pagination,SWAPIController.PeopleCallback callback);
}
