package com.example.dungeons_and_dragons.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;
@Entity(tableName = "CHARACTER_TABLE")
public class Character implements Comparable<Character> {


    public Character(String name, int level, int strength, int constitution, int dexterity, int intelligence, int wisdom, int charisma) {
        this.name = name;
        this.level = level;
        this.strength = strength;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }
        @PrimaryKey(autoGenerate = true)
        private int characterId;

        @ForeignKey(entity = Campaign.class, parentColumns ="campaignId" , childColumns = "characterId" )
        private int campaignId;
        @NonNull
        @ColumnInfo(name = "CHARACTER_NAME_COL")
        private String name;

        @NonNull
        @ColumnInfo(name = "LEVEL_COL")
        private int level;

        @ColumnInfo(name = "STRENGTH_COL")
        private int strength;

        @ColumnInfo(name = "CONSTITUTION_COL")
        private int constitution;

        @ColumnInfo(name = "DEXTERITY_COL")
        private int dexterity;

        @ColumnInfo(name = "INTELLIGENCE_COL")
        private int intelligence;

        @ColumnInfo(name = "WISDOM_COL")
        private int wisdom;

        @ColumnInfo(name = "CHARISMA_COL")
        private int charisma;

        @ColumnInfo(name = "INITIATIVE_COL", defaultValue = "0")
        private int charInit;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getCharInit() { return charInit; }

    public void setCharInit(int charInit) { this.charInit = charInit; }

    @Override
    public int compareTo(Character o) {
        return this.charInit - o.getCharInit();
    }
}

