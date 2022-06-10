package com.nestdev.memorypetproject.data.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TrialsTable (
    @PrimaryKey(autoGenerate = true) val _id: Int, //первичный ключ таблицы, ключ связывания foreign key
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,

    @ColumnInfo(name = "birthday") val birthday: String?,

    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "trial_0") val trial0: Int,
    @ColumnInfo(name = "trial_1") val trial1: Int,
    @ColumnInfo(name = "trial_2") val trial2: Int,
    @ColumnInfo(name = "trial_3") val trial3: Int,
    @ColumnInfo(name = "trial_deferred") val trialDeferred: Int
)