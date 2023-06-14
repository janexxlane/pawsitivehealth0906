package com.example.pawsitivehealth3005.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface StammdatenDAO_Datumheute {

@Insert
void insertStammdaten(StammdatenEntity stammdaten);

    @Query("SELECT * FROM dbeintrag")
    List<StammdatenEntity> getStammdaten();
}
