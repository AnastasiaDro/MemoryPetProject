package com.nestdev.memorypetproject.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TrialsTable::class, WordsTable::class], version = 1)
abstract class AppDataBase : RoomDatabase () {

    abstract fun trialsTableDao(): TrialsTableDao

    abstract fun wordsTableDao(): WordsTableDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    "app_database")
                    .createFromAsset("database/.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
