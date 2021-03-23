package com.example.dungeons_and_dragons.data;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dungeons_and_dragons.model.Campaign;
import com.example.dungeons_and_dragons.model.Character;
import com.example.dungeons_and_dragons.model.Monster;
import com.example.dungeons_and_dragons.model.User;

import java.util.List;

@Dao
public interface DAndDDao {
    //CRUD
    @Insert
    void insert(User user);

    @Insert
    void insert(Campaign campaign);

    @Insert
    void insert(Character character);

    @Insert
    void insert(Monster monster);

    @Query("DELETE FROM USER_TABLE")
    void deleteAll();

    @Query("DELETE FROM USER_TABLE WHERE userId = :id")
    int deleteAUserId(int id);

    @Query("SELECT * FROM USER_TABLE ORDER BY USERNAME_COL DESC")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM CAMPAIGN_TABLE ORDER BY CAMPAIGN_NAME_COL DESC")
    LiveData<List<Campaign>> getAllCampaigns();

    @Query("SELECT * FROM CHARACTER_TABLE ORDER BY CHARACTER_NAME_COL DESC")
    LiveData<List<Character>> getAllCharacters();

    @Query("SELECT * FROM MONSTER_TABLE ORDER BY NAME_COL DESC")
    LiveData<List<Monster>> getAllMonsters();

    @Query("SELECT * FROM USER_TABLE WHERE USERNAME_COL = :username AND PASSWORD_COL = :password")
    User getUser(String username, String password);

    @Update
    void update(Character character);

    @Update
    void update(User user);

    @Update
    void update(Campaign campaign);

    @Update
    void update(Monster monster);

    @Delete
    void delete(User user);

    @Delete
    void delete(Campaign campaign);

    @Delete
    void delete(Character character);

    @Delete
    void delete(Monster monster);





}
