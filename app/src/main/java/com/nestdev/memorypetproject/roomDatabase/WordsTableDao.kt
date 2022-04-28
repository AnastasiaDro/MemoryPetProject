package com.nestdev.memorypetproject.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordsTableDao {
    @Query("SELECT * FROM wordsTable")
    fun getAll(): MutableList<WordsTable>

    @Query("SELECT * FROM wordsTable WHERE _id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<WordsTable>

    @Insert
    fun insertAll(vararg users: WordsTable)

    @Delete
    fun delete(wordsTable: WordsTable)
}