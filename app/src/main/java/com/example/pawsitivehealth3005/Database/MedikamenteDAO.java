package com.example.pawsitivehealth3005.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedikamenteDAO {

    @Insert
    void insertMedikament(MedikamenteEntity medikamenteEntity);

    @Query("SELECT * FROM medikamenteTabelle")
    List<MedikamenteEntity> getMedikamente();
    }
