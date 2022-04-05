package com.nestdev.memorypetproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DB_NAME = "MEMORY_TESTS"

class SQLiteHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    /**
     * Создать и заполнить таблицу
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE WORDS_SET_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, WORD_0 TEXT, WORD_1 TEXT, WORD_2 TEXT, WORD_3 TEXT, WORD_4 TEXT, WORD_5 TEXT, WORD_6 TEXT, WORD_7 TEXT, WORD_8 TEXT, WORD_9 TEXT)")
        db?.execSQL("INSERT INTO WORDS_SET_TABLE(WORD_0, WORD_1, WORD_2, WORD_3, WORD_4, WORD_5, WORD_6, WORD_7, WORD_8, WORD_9) VALUES('Лес', 'Хлеб', 'Окно', 'Стул', 'Вода', 'Брат', 'Конь', 'Гриб', 'Игла', 'Мёд')")
        db?.execSQL("INSERT INTO WORDS_SET_TABLE(WORD_0, WORD_1, WORD_2, WORD_3, WORD_4, WORD_5, WORD_6, WORD_7, WORD_8, WORD_9) VALUES('Дым', 'Сон', 'Шар', 'Пух', 'Звон', 'Куст', 'Час', 'Лёд', 'Ночь', 'Пень')")
        db?.execSQL("INSERT INTO WORDS_SET_TABLE(WORD_0, WORD_1, WORD_2, WORD_3, WORD_4, WORD_5, WORD_6, WORD_7, WORD_8, WORD_9) VALUES('Число', 'Хор', 'Камень', 'Гриб', 'Кино', 'Зонт', 'Море', 'Шмель', 'Лампа', 'Рысь')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}