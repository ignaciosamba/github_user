package com.sambataro.ignacio.anwbassesment.data.repository

import androidx.lifecycle.LiveData
import com.sambataro.ignacio.anwbassesment.data.network.response.CurrentUserReposResponse

interface OwnerRepository {
    suspend fun getCurrentOwner() : LiveData<List<CurrentUserReposResponse>>
}