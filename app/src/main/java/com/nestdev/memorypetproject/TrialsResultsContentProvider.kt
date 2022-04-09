package com.nestdev.memorypetproject

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class TrialsResultsContentProvider : ContentProvider() {
    companion object {
        val PROVIDER_NAME = "com.nestdev.memorypetproject/TrialsResultsContentProvider"
        val URL = "content://$PROVIDER_NAME/RESULTS_TABLE"
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
        return db.query("RESULTS_TABLE", cols, condition, condition_val, null, null, order)
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.nestdev.resultsTable"
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("RESULTS_TABLE", null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete("RESULTS_TABLE", condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        val count: Int = db.update("RESULTS_TABLE", cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }
}
