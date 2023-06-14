package com.example.pawsitivehealth3005.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "spaziergängetabelle")
public class SpaziergängeEntity {



    @PrimaryKey(autoGenerate = true)
    private int id;
    private String spaziergangName;
    private String stepCount;

    private boolean happy;

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean isMedium() {
        return medium;
    }

    public void setMedium(boolean medium) {
        this.medium = medium;
    }

    public boolean isSad() {
        return sad;
    }

    public void setSad(boolean sad) {
        this.sad = sad;
    }

    private boolean medium;
    private boolean sad;

    public void setSpaziergangName(String spaziergangName) {
        this.spaziergangName = spaziergangName;
    }
    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getSpaziergangName() {
        return spaziergangName;
    }

    public String getStepCount() {
        return stepCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }}