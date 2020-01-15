package com.sambataro.ignacio.anwbassesment.ui.jakewharton

import androidx.lifecycle.ViewModel
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository
import com.sambataro.ignacio.anwbassesment.internal.lazyDeferred

class JakeWhartonViewModel(
    private val ownerRepository: OwnerRepository
) : ViewModel() {

    val user by lazyDeferred {
        ownerRepository.getCurrentOwner("JakeWharton")
    }
}