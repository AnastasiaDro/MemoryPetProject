package com.nestdev.memorypetproject

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TenWordsViewModel : ViewModel() {
    private val mutableCounterData by lazy {
        MutableLiveData<Int>()
    }
    val counterData : LiveData<Int> = mutableCounterData


    var counter = 0

    fun onButtonPressed() {
        mutableCounterData.setValue(++counter)
    }

    companion object {
        fun create() = TenWordsViewModel()
    }
}