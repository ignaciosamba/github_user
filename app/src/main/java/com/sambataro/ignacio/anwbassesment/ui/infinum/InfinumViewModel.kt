package com.sambataro.ignacio.anwbassesment.ui.infinum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository
import com.sambataro.ignacio.anwbassesment.internal.lazyDeferred

class InfinumViewModel(
    private val ownerRepository: OwnerRepository
) : ViewModel() {

    val userName = MutableLiveData<String>()

    fun setUserName(user: String){
        userName.setValue(user)
    }

    val user by lazyDeferred {
        ownerRepository.getCurrentOwner(userName.value.toString())
    }

}