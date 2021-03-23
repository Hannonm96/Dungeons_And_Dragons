package com.example.dungeons_and_dragons.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "USER_TABLE")
public class User {
        @PrimaryKey(autoGenerate = true)
        private int userId;

    @NonNull
        @ColumnInfo(name = "USERNAME_COL")
        private String username;
    @NonNull
        @ColumnInfo(name = "NAME_COL")
        private String name;
    @NonNull
        @ColumnInfo(name = "EMAIL_COL")
        private String email;
    @NonNull
        @ColumnInfo(name = "PASSWORD_COL")
        private String password;


    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
            return userId;
        }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

