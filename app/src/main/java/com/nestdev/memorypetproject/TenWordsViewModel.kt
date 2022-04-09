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
import java.sql.Types.NULL


class TenWordsViewModel : ViewModel() {
    private var wordsCursor: Cursor? = null
    private var resultsCursor: Cursor? = null
    private var resultsArray = Array(5) { 0 }
    private var resultsIndex = 0
    private var wordsArray : MutableList<String?>? = ArrayList()
    private val mutableIsCursorReadyFlow = MutableStateFlow<Boolean>(false)
    val isCursorReadyFlow: StateFlow<Boolean> = mutableIsCursorReadyFlow
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
        viewModelScope.launch {
            wordsCursor = context.contentResolver.query(WordsContentProvider.CONTENT_URI,
                arrayOf(WordsContentProvider._ID, WordsContentProvider.wordsList[0], WordsContentProvider.wordsList[1], WordsContentProvider.wordsList[2],
                    WordsContentProvider.wordsList[3], WordsContentProvider.wordsList[4], WordsContentProvider.wordsList[5], WordsContentProvider.wordsList[6],
                    WordsContentProvider.wordsList[7], WordsContentProvider.wordsList[8], WordsContentProvider.wordsList[9]), null, null, WordsContentProvider._ID)
            mutableIsCursorReadyFlow.emit(true)
        }
        //TODO
//        viewModelScope.launch {
//            resultsCursor = context.contentResolver.query(TrialsResultsContentProvider.CONTENT_URI,
//                arrayOf(WordsContentProvider._ID, TrialsResultsContentProvider.)
//                )
//        }
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
        viewModelScope.launch {
            resultsCursor
        }
    }



    fun onStringFinishingPressed(result: Int) {
        if (resultsIndex < 5)  {
            resultsArray[resultsIndex] = result
            resultsIndex++
        } else {
            //TODO отправить в базу данных
        }
    }


    fun onTextViewPressed() {
        if (counter < 10)
            counter++
        mutableCounterData.value = counter
    }

    fun finishTrial() {
        counter = 0

    }

}
