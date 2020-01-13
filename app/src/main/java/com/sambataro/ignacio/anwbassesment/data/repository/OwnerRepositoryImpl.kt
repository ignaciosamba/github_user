package com.sambataro.ignacio.anwbassesment.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.sambataro.ignacio.anwbassesment.data.db.CurrentOwnerDAO
import com.sambataro.ignacio.anwbassesment.data.network.OwnerNetworkDataSource
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.acl.Owner

class OwnerRepositoryImpl(
    private val currentUserDAO: CurrentOwnerDAO,
    private val ownerNetworkDataSource : OwnerNetworkDataSource
) : OwnerRepository {

    init {
        ownerNetworkDataSource.downloadCurrentUserRepo.observeForever {
            newCurrentOwner -> persistFetchCurrentOwner(newCurrentOwner)
        }
    }

    private suspend fun initUser() {
        fetchCurrentOwnerData()
    }

    override suspend fun getCurrentOwner(): LiveData<List<CurrentUserReposResponse>> {

        return withContext(Dispatchers.IO) {
            initUser()
            return@withContext currentUserDAO.getCurrentOwner()
        }
    }

    private fun persistFetchCurrentOwner(fetchedOwner: List<CurrentUserReposResponse>) {
        GlobalScope.launch(Dispatchers.IO){
            Log.d("SAMBALOIDE", "persistFetchCurrentOwner has a value of: " + fetchedOwner[0].owner.login)
            Log.d("SAMBALOIDE", "persistFetchCurrentOwner has a size of: " + fetchedOwner.size)
            var rowId : Long = 0
            for (item in fetchedOwner) {
                 rowId = currentUserDAO.upsert(item)
            }
            Log.d("SAMBALOIDE", "DAO has update the:" + rowId)
        }
    }

    private suspend fun fetchCurrentOwnerData() {
        ownerNetworkDataSource.fetchCurrentUserRepo("Infinum")
    }


}