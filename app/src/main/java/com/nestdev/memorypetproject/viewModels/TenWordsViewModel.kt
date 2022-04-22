package com.nestdev.memorypetproject.viewModels

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nestdev.memorypetproject.DatabaseContentProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TenWordsViewModel : ViewModel() {
    private var wordsCursor: Cursor? = null
    private var resultsCursor: Cursor? = null
    private var resultsArray = Array(5) { 0 }
    private var resultsIndex = 0
    var trialCounter = 0
    private var trialsResultsList = mutableListOf<Int>(0, 0, 0, 0, 0)
    private var wordsArray : MutableList<String?>? = ArrayList()
    private val mutableIsWordsCursorReadyFlow = MutableStateFlow<Boolean>(false)
    val isWordsCursorReadyFlow: StateFlow<Boolean> = mutableIsWordsCursorReadyFlow
    private val mutableIsTrialsCursorReadyFlow = MutableStateFlow<Boolean>(false)
    val isTrialsCursorReadyFlow: StateFlow<Boolean> = mutableIsWordsCursorReadyFlow
    private val mutableListWordsFlow = MutableStateFlow<MutableList<String?>?>(null)
    val listWordsFlow: StateFlow<MutableList<String?>?> = mutableListWordsFlow

    private val mutableActiveTrialLiveData by lazy {
        MutableLiveData<Int>()
    }

    private val mutableCounterData by lazy {
        MutableLiveData<Int>()
    }
    val counterData : LiveData<Int> = mutableCounterData
    var counter = 0

    fun getCursors(context: Context) {
        with(DatabaseContentProvider) {
            viewModelScope.launch {
                wordsCursor = context.contentResolver.query(
                    WORDS_CONTENT_URI,
                    arrayOf( _ID, wordsList[0], wordsList[1], wordsList[2], wordsList[3], wordsList[4], wordsList[5], wordsList[6], wordsList[7], wordsList[8], wordsList[9]), null, null, _ID
                )
                mutableIsWordsCursorReadyFlow.emit(true)
            }
            viewModelScope.launch {
                resultsCursor = context.contentResolver.query(
                    TRIALS_CONTENT_URI,
                    arrayOf(_ID, trialsColumnList[0], trialsColumnList[1], trialsColumnList[2], trialsColumnList[3], trialsColumnList[4], trialsColumnList[5], trialsColumnList[6], trialsColumnList[7], trialsColumnList[8]), null, null, _ID
                )
                mutableIsTrialsCursorReadyFlow.emit(true)
            }
        }
    }

    fun getWordsSet(rowNumber: Int) {
        viewModelScope.launch {
            wordsCursor?.moveToFirst()
            for (i in 0 until rowNumber) //сеты слов будут нумероваться с 1
                wordsCursor?.moveToNext()
            if (wordsCursor != null) {
                for (i in 0..9) {
                    println(wordsCursor?.getString(i + 1))
                    wordsArray?.add(i, wordsCursor?.getString(i + 1))
                }
            }
            mutableListWordsFlow.emit(wordsArray)
        }
    }

    fun onTextViewPressed() {
        if (counter < 10)
            counter++
        mutableCounterData.value = counter
    }

    fun finishTrial(context: Context) {
        trialsResultsList[trialCounter] = counter
        counter = 0
        if (trialCounter == 4) {
            Toast.makeText(context, "Тест окончен!", Toast.LENGTH_LONG).show()
            context.contentResolver.insert(DatabaseContentProvider.TRIALS_CONTENT_URI, generateContentValuesForTrialsTable())
            trialCounter = 0
            return
        }
        trialCounter++
    }


    private fun generateContentValuesForTrialsTable() : ContentValues {
        val contentValues = ContentValues(9)
        with(DatabaseContentProvider) {
            //TODO Добавить добавление данных об испытуемом
            contentValues.put(trialsColumnList[0], "NO_NAME")  //NAME
            contentValues.put(trialsColumnList[1], "NO_SURNAME") //SURNAME
            contentValues.put(trialsColumnList[2], "NO_BIRTHDAY") //BIRTHDAY
            contentValues.put(trialsColumnList[3], "NO_DATE")   //DATE
            contentValues.put(trialsColumnList[4], trialsResultsList[0]) //TRIAL_0
            contentValues.put(trialsColumnList[5], trialsResultsList[1]) //TRIAL_1
            contentValues.put(trialsColumnList[6], trialsResultsList[2]) //TRIAL_2
            contentValues.put(trialsColumnList[7], trialsResultsList[3]) //TRIAL_3
            contentValues.put(trialsColumnList[8], trialsResultsList[4]) //TRIAL_DEFERRED
        }
        return contentValues
    }
}
