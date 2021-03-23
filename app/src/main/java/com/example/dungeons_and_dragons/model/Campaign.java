package com.example.dungeons_and_dragons.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "CAMPAIGN_TABLE")
public class Campaign {
    @PrimaryKey(autoGenerate = true)
    private int campaignId;

    @ForeignKey(entity = User.class, parentColumns ="userId" , childColumns = "campaignId" )
    private int userId;
    @NonNull
    @ColumnInfo(name = "CAMPAIGN_NAME_COL")
    private String name;

    public Campaign(String name) {
        this.name = name;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
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

}

