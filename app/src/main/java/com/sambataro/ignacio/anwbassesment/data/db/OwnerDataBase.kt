package com.sambataro.ignacio.anwbassesment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sambataro.ignacio.anwbassesment.data.db.entity.Owner
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse


//TODO: Change the Owner class per CurrentUserReposResponse

@Database(
    entities = [CurrentUserReposResponse::class],
    version = 1)
abstract class OwnerDataBase : RoomDatabase(){

    abstract fun currentOwnerDAO() : CurrentOwnerDAO

    companion object {
        @Volatile private var instance : OwnerDataBase?= null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance?: synchronized(LOCK) {
            instance ?: buildDataBase(context).also { instance = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                OwnerDataBase::class.java, "ownerData.db")
                .build()
    }
}