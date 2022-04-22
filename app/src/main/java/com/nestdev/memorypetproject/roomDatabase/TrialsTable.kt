package com.nestdev.memorypetproject.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TrialsTable (
    @PrimaryKey val _id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,

    @ColumnInfo(name = "birthday") val birthday: Date?,

    @ColumnInfo(name = "last_name") val date: Date?,
    @ColumnInfo(name = "last_name") val trial0: Int,
    @ColumnInfo(name = "last_name") val trial1: Int,
    @ColumnInfo(name = "last_name") val trial2: Int,
    @ColumnInfo(name = "last_name") val trial3: Int,
    @ColumnInfo(name = "last_name") val trialDeferred: Int
)