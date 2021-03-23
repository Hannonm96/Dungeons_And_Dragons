package com.example.dungeons_and_dragons.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dungeons_and_dragons.model.Campaign;
import com.example.dungeons_and_dragons.model.Character;
import com.example.dungeons_and_dragons.model.Monster;
import com.example.dungeons_and_dragons.model.User;

import io.reactivex.annotations.NonNull;

@Database(entities = {User.class, Campaign.class, Character.class, Monster.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    public static volatile AppDB INSTANCE;

    public abstract DAndDDao dAndDDao();

    public static AppDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    //create the db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "DAndDDatabase")
                            .fallbackToDestructiveMigration()
                           // .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
/**
    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(INSTANCE).execute();
        }
    };

    public static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private DAndDDao dAndDDao;

        private PopulateDBAsyncTask(AppDB appDB) {
            dAndDDao = appDB.dAndDDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dAndDDao.insert(new User("Fred Farbarckle", "Fred", "fred@Fardbarkle.com", "qwerty"));
            dAndDDao.insert(new User("Beanie Baby", "JumpingBean", "bean@kachigga.com", "bean"));
            dAndDDao.insert(new User("KD Curts", "GrouchAss", "kd@kdc.com", "meanie"));
            dAndDDao.insert(new Campaign("Curse of Strahd"));
            dAndDDao.insert(new Campaign("Out of the Abyss"));
            dAndDDao.insert(new Campaign("Storm King’s Thunder"));
            dAndDDao.insert(new Character("Jeff", 8, 13, 11, 9, 5, 19, 5));
            dAndDDao.insert(new Character("Rakkim", 12, 9, 20, 3, 20, 14, 11));
            dAndDDao.insert(new Character("Alexander", 4, 11,5, 19, 3, 12, 9));
            dAndDDao.insert(new Monster("Monster 1", "5"));
            dAndDDao.insert(new Monster("Monster 2", "12"));
            dAndDDao.insert(new Monster("Monster 3", "500"));
            return null;
        }
    }
        */
}
