package com.sambataro.ignacio.anwbassesment.ui.jakewharton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository
import com.sambataro.ignacio.anwbassesment.ui.infinum.InfinumViewModel

class JakeWhartonViewModelFactory(
    private val ownerRepository: OwnerRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JakeWhartonViewModel(ownerRepository) as T
    }

}