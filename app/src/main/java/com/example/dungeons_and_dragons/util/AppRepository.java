package com.example.dungeons_and_dragons.util;

import android.app.Application;
import android.media.midi.MidiOutputPort;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.example.dungeons_and_dragons.data.AppDB;
import com.example.dungeons_and_dragons.data.DAndDDao;
import com.example.dungeons_and_dragons.model.Campaign;
import com.example.dungeons_and_dragons.model.Character;
import com.example.dungeons_and_dragons.model.Monster;
import com.example.dungeons_and_dragons.model.User;

import org.w3c.dom.CharacterData;

import java.util.List;

public class AppRepository {
    private DAndDDao dAndDDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Campaign>> allCampaigns;
    private LiveData<List<Character>> allCharacters;
    private LiveData<List<Monster>> allMonsters;


    public AppRepository(Application application) {
        //Get data from a remote API and then put it on a diff. list
        AppDB db = AppDB.getDatabase(application);
        dAndDDao = db.dAndDDao();
        allUsers = dAndDDao.getAllUser();
        allCampaigns = dAndDDao.getAllCampaigns();
        allCharacters = dAndDDao.getAllCharacters();
        allMonsters = dAndDDao.getAllMonsters();


    }
    // methods to return a list of classes
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<Campaign>> getAllCampaigns() { return  allCampaigns; }

    public LiveData<List<Character>> getAllCharacters() { return  allCharacters; }

    public LiveData<List<Monster>> getAllMonsters() { return allMonsters; }

    public User findUser(String username, String password) {
        return dAndDDao.getUser(username, password);

    }

    // insert DAO
    public void insert(User user) {
        new InsertUserAsyncTask(dAndDDao).execute(user);
    }

    public void insert(Campaign campaign) { new InsertCampaignAsyncTask(dAndDDao).execute(campaign); }

    public void insert(Character character) { new InsertCharacterAsyncTask(dAndDDao).execute(character); }

    public void insert(Monster monster) { new InsertMonsterAsyncTask(dAndDDao).execute(monster); }

    // update DAO
    public void update(User user) { new UpdateUserAsyncTask(dAndDDao).execute(user); }

    public void update(Campaign campaign) { new UpdateCampaignAsyncTask(dAndDDao).execute(campaign); }

    public void update(Character character) { new UpdateCharacterAsyncTask(dAndDDao).execute(character); }

    public void update(Monster monster) { new UpdateMonsterAsyncTask(dAndDDao).execute(monster); }

    // delete DAO
    public void delete(User user) { new DeleteUserAsyncTask(dAndDDao).execute(user); }

    public void delete(Campaign campaign) { new DeleteCampaignAsyncTask(dAndDDao).execute(campaign); }

    public void delete(Character character) { new DeleteCharacterAsyncTask(dAndDDao).execute(character); }

    public void delete(Monster monster) { new DeleteMonsterAsyncTask(dAndDDao).execute(monster); }

    // classes to insert using Async Task
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private DAndDDao asyncTaskDao;

        public InsertUserAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(User... users) {
            //[obj1, obj2....]
            asyncTaskDao.insert(users[0]);
            return null;
        }
    }

    private static class InsertCampaignAsyncTask extends AsyncTask<Campaign, Void, Void> {
        private DAndDDao asyncTaskDao;

        public InsertCampaignAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Campaign... campaigns) {
            //[obj1, obj2....]
            asyncTaskDao.insert(campaigns[0]);
            return null;
        }
    }

    private static class InsertCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private DAndDDao asyncTaskDao;

        public InsertCharacterAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Character... characters) {
            //[obj1, obj2....]
            asyncTaskDao.insert(characters[0]);
            return null;
        }

    }

    private static class InsertMonsterAsyncTask extends AsyncTask<Monster, Void, Void> {
        private DAndDDao asyncTaskDao;

        public InsertMonsterAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Monster... monsters) {
            //[obj1, obj2....]
            asyncTaskDao.insert(monsters[0]);
            return null;
        }

    }
    // update using async task
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private DAndDDao asyncTaskDao;

        public UpdateUserAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(User... users) {
            //[obj1, obj2....]
            asyncTaskDao.update(users[0]);
            return null;
        }

    }
    private static class UpdateCampaignAsyncTask extends AsyncTask<Campaign, Void, Void> {
        private DAndDDao asyncTaskDao;

        public UpdateCampaignAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Campaign... campaigns) {
            //[obj1, obj2....]
            asyncTaskDao.update(campaigns[0]);
            return null;
        }

    }

    private static class UpdateCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private DAndDDao asyncTaskDao;

        public UpdateCharacterAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Character... characters) {
            //[obj1, obj2....]
            asyncTaskDao.update(characters[0]);
            return null;
        }

    }

    private static class UpdateMonsterAsyncTask extends AsyncTask<Monster, Void, Void> {
        private DAndDDao asyncTaskDao;

        public UpdateMonsterAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Monster... monsters) {
            //[obj1, obj2....]
            asyncTaskDao.update(monsters[0]);
            return null;
        }

    }

    // delete using async task
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private DAndDDao asyncTaskDao;

        public DeleteUserAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(User... users) {
            //[obj1, obj2....]
            asyncTaskDao.delete(users[0]);
            return null;
        }

    }

    private static class DeleteCampaignAsyncTask extends AsyncTask<Campaign, Void, Void> {
        private DAndDDao asyncTaskDao;

        public DeleteCampaignAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Campaign... campaigns) {
            //[obj1, obj2....]
            asyncTaskDao.delete(campaigns[0]);
            return null;
        }

    }

    private static class DeleteCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private DAndDDao asyncTaskDao;

        public DeleteCharacterAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Character... characters) {
            //[obj1, obj2....]
            asyncTaskDao.delete(characters[0]);
            return null;
        }

    }

    private static class DeleteMonsterAsyncTask extends AsyncTask<Monster, Void, Void> {
        private DAndDDao asyncTaskDao;

        public DeleteMonsterAsyncTask(DAndDDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Monster... monsters) {
            //[obj1, obj2....]
            asyncTaskDao.delete(monsters[0]);
            return null;
        }

    }

}
