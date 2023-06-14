package com.example.pawsitivehealth3005.Database;


import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Dao
public interface SpaziergängeDAO {


    @Insert
    void insertSpaziergänge(SpaziergängeEntity spaziergängeEntity);

    @Query("SELECT * FROM spaziergängetabelle")
    List<SpaziergängeEntity> getSpaziergänge();
    }

