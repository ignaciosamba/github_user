package com.sambataro.ignacio.anwbassesment.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse

@Dao
interface CurrentOwnerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(ownerEntry : CurrentUserReposResponse) : Long

    @Query("select * from current_user where owner_login= :userName")
    fun getCurrentOwner(userName : String): LiveData<List<CurrentUserReposResponse>>
}