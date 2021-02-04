package com.shaadi.demo.dao.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.demo.model.Profile

@Dao
interface LocalProfileDao {
    @Query("SELECT * FROM profile_table")
    suspend fun getProfiles() : List<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg profile: Profile)

    @Query("DELETE FROM profile_table")
    suspend fun deleteAll()
}