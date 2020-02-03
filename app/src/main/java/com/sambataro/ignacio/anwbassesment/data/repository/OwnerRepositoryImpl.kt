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

    private suspend fun initUser(user: String) {
//        val lastUser = currentUserDAO.getCurrentOwnerNonLive(user)
//        if (lastUser == null || fetchUserIsNeeded(user, lastUser))
            fetchCurrentOwnerData(user)
    }

    private fun fetchUserIsNeeded(user: String, lastUser : List<CurrentUserReposResponse>): Boolean{
        var isNeeded = true
        for (item in lastUser) {
            isNeeded = user != item.owner.login
        }
        return isNeeded
    }

    override suspend fun getCurrentOwner(user: String): LiveData<List<CurrentUserReposResponse>> {

        return withContext(Dispatchers.IO) {
            initUser(user)
            return@withContext currentUserDAO.getCurrentOwner(user)
        }
    }

    private fun persistFetchCurrentOwner(fetchedOwner: List<CurrentUserReposResponse>) {
        GlobalScope.launch(Dispatchers.IO){
            for (item in fetchedOwner) {
                 currentUserDAO.upsert(item)
            }
        }
    }

    private suspend fun fetchCurrentOwnerData(user: String) {
        ownerNetworkDataSource.fetchCurrentUserRepo(user)
    }


}