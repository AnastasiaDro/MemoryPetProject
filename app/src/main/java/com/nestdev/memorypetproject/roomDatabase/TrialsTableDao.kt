package com.nestdev.memorypetproject.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrialsTableDao {
    @Query("SELECT * FROM trialsTable")
    fun getAll(): Flow<List<TrialsTable>>

    @Query("SELECT * FROM trialsTable WHERE _id IN (:ids)")
    fun loadAllByIds(ids: IntArray): Flow<List<TrialsTable>>

    @Query("SELECT * FROM trialsTable WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): TrialsTable

    @Insert
    suspend fun insert(trial: TrialsTable)

    @Delete
    fun delete(trial: TrialsTable)
}