package com.sambataro.ignacio.anwbassesment.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel(){

    val _message = MutableLiveData<String>()

    val message: LiveData<String>
        get() = _message

    fun setMsgCommunicator(msg:String){
        _message.postValue(msg)
    }
}