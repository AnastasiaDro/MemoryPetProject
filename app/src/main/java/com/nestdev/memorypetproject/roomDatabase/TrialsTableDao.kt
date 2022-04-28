package com.nestdev.memorypetproject.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrialsTableDao {
    @Query("SELECT * FROM trialsTable")
    fun getAll(): MutableList<TrialsTable>

    @Query("SELECT * FROM trialsTable WHERE _id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<TrialsTable>

    @Query("SELECT * FROM trialsTable WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): TrialsTable

    @Insert
    fun insertAll(vararg trials: TrialsTable)

    @Delete
    fun delete(trial: TrialsTable)
}