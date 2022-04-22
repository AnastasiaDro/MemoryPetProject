package com.nestdev.memorypetproject.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrialsTable::class, WordsTable::class], version = 1)
abstract class DataBase : RoomDatabase () {

    abstract fun trialsTableDao(): TrialsTable

    abstract fun wordsTableDao(): WordsTableDao
}
