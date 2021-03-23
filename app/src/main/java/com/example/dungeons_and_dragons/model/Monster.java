package com.example.dungeons_and_dragons.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MONSTER_TABLE")
public class Monster {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "NAME_COL")
    private String name;
    @ColumnInfo(name = "LEVEL_COL")
    private String level;

    public Monster(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
