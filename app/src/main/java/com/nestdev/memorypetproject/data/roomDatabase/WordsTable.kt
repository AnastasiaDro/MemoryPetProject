package com.nestdev.memorypetproject.data.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordsTable (
    @PrimaryKey val _id: Int?,
    @ColumnInfo(name = "WORD_0") val word0: String?,
    @ColumnInfo(name = "WORD_1") val word1: String?,
    @ColumnInfo(name = "WORD_2") val word2: String?,
    @ColumnInfo(name = "WORD_3") val word3: String?,
    @ColumnInfo(name = "WORD_4") val word4: String?,
    @ColumnInfo(name = "WORD_5") val word5: String?,
    @ColumnInfo(name = "WORD_6") val word6: String?,
    @ColumnInfo(name = "WORD_7") val word7: String?,
    @ColumnInfo(name = "WORD_8") val word8: String?,
    @ColumnInfo(name = "WORD_9") val word9: String?
) {
    fun getWordsArray() : Array<String?> {
        return arrayOf(word0, word1, word2, word3, word4, word5, word6, word7, word8, word9)
    }
}
