package com.sambataro.ignacio.anwbassesment.ui.infinum

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepositoryImpl
import com.sambataro.ignacio.anwbassesment.internal.lazyDeferred

class InfinumViewModel(
    private val ownerRepository: OwnerRepository
) : ViewModel() {

    val user by lazyDeferred {
        ownerRepository.getCurrentOwner()
    }

}