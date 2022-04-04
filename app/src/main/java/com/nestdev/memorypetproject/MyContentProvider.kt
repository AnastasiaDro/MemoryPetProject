package com.nestdev.memorypetproject

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {
    companion object {
        val PROVIDER_NAME = "com.nestdev.memorypetproject/MyContentProvider"
        val URL = "content://$PROVIDER_NAME/ACTABLE"
        val CONTENT_URI = Uri.parse(URL)

        val _ID = "_id"
    }
    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = SQLiteHelper(context)
        db = helper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("WORDS_SET_TABLE", cols, condition, condition_val, null, null, order)
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.cursor.dir/vnd.nestdev.wordsSetTable"
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("WORDS_SET_TABLE", null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete("WORDS_SET_TABLE", condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        val count: Int = db.update("WORDS_SET_TABLE", cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }
}