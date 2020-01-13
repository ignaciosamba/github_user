package com.sambataro.ignacio.anwbassesment.data.network

import androidx.lifecycle.LiveData
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse

interface OwnerNetworkDataSource {

    val downloadCurrentUserRepo : LiveData<List<CurrentUserReposResponse>>

    suspend fun fetchCurrentUserRepo (user: String)
}