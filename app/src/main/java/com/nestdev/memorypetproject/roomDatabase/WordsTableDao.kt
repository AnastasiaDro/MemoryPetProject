package com.nestdev.memorypetproject.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

typealias wordsTable =  WORDS_SET_TABLE

@Dao
interface WordsTableDao {
    @Query("SELECT * FROM words_set_table")
    fun getAll(): Flow<List<wordsTable>>

    @Query("SELECT * FROM words_set_table WHERE _id = :id")
    fun loadAllById(id: Int): Flow<wordsTable>

    @Insert
    fun insertAll(vararg users: wordsTable)

    @Delete
    fun delete(wordsTable: WORDS_SET_TABLE)
}