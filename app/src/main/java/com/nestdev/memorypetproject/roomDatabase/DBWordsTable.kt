package com.nestdev.memorypetproject.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBWordsTable (
    @PrimaryKey val _id: Int,
    @ColumnInfo(name = "word_0") val word0: String,
    @ColumnInfo(name = "word_1") val word1: String,
    @ColumnInfo(name = "word_2") val word2: String,
    @ColumnInfo(name = "word_3") val word3: String,
    @ColumnInfo(name = "word_4") val word4: String,
    @ColumnInfo(name = "word_5") val word5: String,
    @ColumnInfo(name = "word_6") val word6: String,
    @ColumnInfo(name = "word_7") val word7: String,
    @ColumnInfo(name = "word_8") val word8: String,
    @ColumnInfo(name = "word_9") val word9: String
)
