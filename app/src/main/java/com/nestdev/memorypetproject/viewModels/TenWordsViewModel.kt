package com.nestdev.memorypetproject.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.room.Room
import com.nestdev.memorypetproject.roomDatabase.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class TenWordsViewModel(private val wordsTableDao: WordsTableDao, private val trialsTableDao: TrialsTableDao) : ViewModel() {

    /**
     * Поля таблицы проб
     */
    private var name = "NONAME"
    private var surname = "NOSURNAME"
    private var birthday = LocalDate.now().toString()
    private var date = LocalDate.now().toString()
    private var trialsResultsList = mutableListOf(0, 0, 0, 0, 0)
    var testStatusMessage = "Строка!"

    var trialCounter = 0

    /**
     * Room DataBase
     */
    private lateinit var db: AppDatabase
    private val mutableCounterData by lazy {
        MutableLiveData<Int>()
    }
    val counterData : LiveData<Int> = mutableCounterData
    var counter = 0

    /**
     * Инициализация базы данных
     */
    fun initDatabase(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    fun fullWordsSet(index: Int): Flow<WordsTable> = wordsTableDao.loadAllById(index)

    fun onTextViewPressed() {
        if (counter < 10)
            counter++
        mutableCounterData.value = counter
    }

    fun finishTrial() = runBlocking {
        if (trialCounter in 0..4) trialsResultsList[trialCounter] = counter
        counter = 0
        if (trialCounter == 4) {
            println("JОКОНЧЕН")
            testStatusMessage = "Проба окончена!"
            val trial = TrialsTable(0, name, surname, birthday, date, trialsResultsList[0], trialsResultsList[1], trialsResultsList[2], trialsResultsList[3], trialsResultsList[4])
            coroutineScope {
                trialsTableDao.insert(trial)
            }
        }
        trialCounter++
    }
}

class TenWordsViewModelFactory(
    private val wordsTableDao: WordsTableDao, private val trialsTableDao: TrialsTableDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TenWordsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TenWordsViewModel(wordsTableDao, trialsTableDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
