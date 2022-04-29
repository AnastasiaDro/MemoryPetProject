package com.nestdev.memorypetproject.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsTableDao {
    @Query("SELECT * FROM wordsTable")
    fun getAll(): Flow<List<WordsTable>>

    @Query("SELECT * FROM wordsTable WHERE _id = :id")
    fun loadAllById(id: Int): Flow<WordsTable>

    @Insert
    fun insertAll(vararg users: WordsTable)

    @Delete
    fun delete(wordsTable: WordsTable)
}