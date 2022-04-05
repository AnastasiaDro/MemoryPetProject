package com.nestdev.memorypetproject

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TenWordsViewModel : ViewModel() {
    private var cursor: Cursor? = null
    private var wordsArray : MutableList<String?>? = null
    private val mutableIsCursorReadyFlow = MutableStateFlow<Boolean>(false)
    val isCursorReadyFlow: StateFlow<Boolean> = mutableIsCursorReadyFlow
    private val mutableListWordsFlow = MutableStateFlow<MutableList<String?>?>(null)
    val listWordsFlow: StateFlow<MutableList<String?>?> = mutableListWordsFlow

    private val mutableCounterData by lazy {
        MutableLiveData<Int>()
    }
    val counterData : LiveData<Int> = mutableCounterData
    var counter = 0


    fun getCursor(context: Context) {
        viewModelScope.launch {
            cursor = context.contentResolver.query(MyContentProvider.CONTENT_URI,
                arrayOf(MyContentProvider._ID, MyContentProvider.wordsList[0], MyContentProvider.wordsList[1], MyContentProvider.wordsList[2],
                    MyContentProvider.wordsList[3], MyContentProvider.wordsList[4], MyContentProvider.wordsList[5], MyContentProvider.wordsList[6],
                    MyContentProvider.wordsList[7], MyContentProvider.wordsList[8], MyContentProvider.wordsList[9]), null, null, MyContentProvider._ID)
            mutableIsCursorReadyFlow.emit(true)
        }
    }

    fun getWordsSet(rowNumber: Int) {
        viewModelScope.launch {
            cursor?.moveToFirst()
            while (cursor != null || cursor?.getInt(0) != rowNumber) {
                cursor?.moveToNext()
            }
            if (cursor != null) {
                for (i in 0..9) {
                    wordsArray?.add(i, cursor?.getString(i + 1))
                }
            }
            mutableListWordsFlow.emit(wordsArray)
        }
    }


    fun onTextViewPressed() {
        mutableCounterData.value = ++counter
    }


    companion object {
        fun create() = TenWordsViewModel()
    }

}