package com.sambataro.ignacio.anwbassesment.ui.infinum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository

class InfinumViewModelFactory(
    private val ownerRepository: OwnerRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfinumViewModel(ownerRepository) as T
    }

}