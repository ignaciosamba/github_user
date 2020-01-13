package com.sambataro.ignacio.anwbassesment.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sambataro.ignacio.anwbassesment.data.ApiCurrentUser
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse
import com.sambataro.ignacio.anwbassesment.internal.NoConnectivityException

class OwnerNetworkDataSourceImpl(
    private val apiCurrentUser: ApiCurrentUser
) : OwnerNetworkDataSource {

    private val _downloadCurrentUserRepo = MutableLiveData<List<CurrentUserReposResponse>>()

    override val downloadCurrentUserRepo: LiveData<List<CurrentUserReposResponse>>
        get() = _downloadCurrentUserRepo

    override suspend fun fetchCurrentUserRepo(user: String) {
        try {
            val fetchCurrentOwnerRepos = apiCurrentUser
                .getCurrentUserInfo(user)
                .await()
            _downloadCurrentUserRepo.postValue(fetchCurrentOwnerRepos)
            Log.d("SAMBALOIDE", "fetchCurrentUserRepo have: " + (fetchCurrentOwnerRepos[0].owner.login))
            Log.d("SAMBALOIDE", "_downloadCurrentUserRepo MUTABLELIVEDATA have: "
                    + (_downloadCurrentUserRepo.value?.size))
            Log.d("SAMBALOIDE", "downloadCurrentUserRepo LIVEDATA have: "
                    + (downloadCurrentUserRepo.value?.get(0)?.owner?.login))
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet Connection: " + e)
        }
    }
}