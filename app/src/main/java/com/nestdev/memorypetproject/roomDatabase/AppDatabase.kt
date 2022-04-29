package com.nestdev.memorypetproject.roomDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TrialsTable::class, WordsTable::class], version = 1)
abstract class AppDatabase : RoomDatabase () {

    abstract fun trialsTableDao(): TrialsTableDao

    abstract fun wordsTableDao(): WordsTableDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(rdc)
                    //.createFromAsset("database/memory.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }


        private val rdc = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                createWordsSetTable(db)
                createResultsTable(db)
            }
        }

        private fun createWordsSetTable(db: SupportSQLiteDatabase?) {
            db?.execSQL("CREATE TABLE IF NOT EXISTS WORDS_SET_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, WORD_0 TEXT, WORD_1 TEXT, WORD_2 TEXT, WORD_3 TEXT, WORD_4 TEXT, WORD_5 TEXT, WORD_6 TEXT, WORD_7 TEXT, WORD_8 TEXT, WORD_9 TEXT)")
            db?.execSQL("INSERT INTO WORDS_SET_TABLE(WORD_0, WORD_1, WORD_2, WORD_3, WORD_4, WORD_5, WORD_6, WORD_7, WORD_8, WORD_9) VALUES('Лес', 'Хлеб', 'Окно', 'Стул', 'Вода', 'Брат', 'Конь', 'Гриб', 'Игла', 'Мёд')")
            db?.execSQL("INSERT INTO WORDS_SET_TABLE(WORD_0, WORD_1, WORD_2, WORD_3, WORD_4, WORD_5, WORD_6, WORD_7, WORD_8, WORD_9) VALUES('Дым', 'Сон', 'Шар', 'Пух', 'Звон', 'Куст', 'Час', 'Лёд', 'Ночь', 'Пень')")
            db?.execSQL("INSERT INTO WORDS_SET_TABLE(WORD_0, WORD_1, WORD_2, WORD_3, WORD_4, WORD_5, WORD_6, WORD_7, WORD_8, WORD_9) VALUES('Число', 'Хор', 'Камень', 'Гриб', 'Кино', 'Зонт', 'Море', 'Шмель', 'Лампа', 'Рысь')")
        }

        private fun createResultsTable(db: SupportSQLiteDatabase?) {
            db?.execSQL("CREATE TABLE IF NOT EXISTS RESULTS_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME, SURNAME, BIRTHDAY, DATE, TRIAL_0, TRIAL_1, TRIAL_2, TRIAL_3, TRIAL_DEFERRED)")
        }
    }



}
