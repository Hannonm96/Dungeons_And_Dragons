package com.example.dungeons_and_dragons.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dungeons_and_dragons.util.AppRepository;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class AppViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Campaign>> allCampaigns;
    private LiveData<List<Character>> allCharacters;
    private LiveData<List<Monster>> allMonsters;


    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allUsers = appRepository.getAllUsers();
        allCampaigns = appRepository.getAllCampaigns();
        allCharacters = appRepository.getAllCharacters();
        allMonsters = appRepository.getAllMonsters();
    }

    // get the LiveData lists of database objects
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<Campaign>> getAllCampaigns() { return  allCampaigns; }

    public LiveData<List<Character>> getAllCharacters() { return  allCharacters; }

    public LiveData<List<Monster>> getAllMonsters() { return allMonsters; }



    // insert into the database
    public void insert(User user) {
        appRepository.insert(user);
    }

    public void insert(Campaign campaign) {
        appRepository.insert(campaign);
    }

    public void insert(Character character) {
        appRepository.insert(character);
    }

    public void insert(Monster monster) {
        appRepository.insert(monster);
    }

    public User findUser(String username, String password) { return appRepository.findUser(username, password); }

    // update the Database
    public void update(User user) { appRepository.update(user); }

    public void update(Campaign campaign) { appRepository.update(campaign); }

    public void update(Character character) { appRepository.update(character); }

    public void update(Monster monster) { appRepository.update(monster); }

    // delete from the database
    public void delete(User user) { appRepository.delete(user); }

    public void delete(Campaign campaign) { appRepository.delete(campaign); }

    public void delete(Character character) { appRepository.delete(character); }

    public void delete(Monster monster) { appRepository.delete(monster); }
}
