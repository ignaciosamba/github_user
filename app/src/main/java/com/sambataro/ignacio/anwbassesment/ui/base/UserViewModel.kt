package com.sambataro.ignacio.anwbassesment.ui.base

import androidx.lifecycle.ViewModel
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository

class UserViewModel(
    private val ownerRepository: OwnerRepository,
    private val userName: String
) : ViewModel() {

}