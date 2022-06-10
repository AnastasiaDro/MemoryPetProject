package com.nestdev.memorypetproject.data.roomDatabase

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface WordsTableDao {
    @Query("SELECT * FROM wordsTable")
    fun getAll(): Flow<List<WordsTable>>

    @Query("SELECT * FROM wordsTable WHERE _id = :id")
    fun loadAllById(id: Int): Flow<WordsTable>

    @Delete
    fun delete(wordsTable: WordsTable)

    /**
     * Запросы для экспорта базы данных в другое приложение
     */
    @Query("SELECT * FROM wordsTable")
    fun getAllToCursor(): Cursor

    @Query("SELECT * FROM wordsTable WHERE _id = :id")
    fun loadAllByIdToCursor(id: Int): Cursor
}