package com.nestdev.memorypetproject.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class DatabaseContentProvider : ContentProvider() {
    companion object {
        val PROVIDER_NAME = "com.nestdev.memorypetproject/ContentProvider"
        val WORDS_URL = "content://$PROVIDER_NAME/WORDS_SET_TABLE"
        val TRIALS_URL = "content://$PROVIDER_NAME/RESULTS_TABLE"

        val WORDS_CONTENT_URI = Uri.parse(WORDS_URL)
        val TRIALS_CONTENT_URI = Uri.parse(TRIALS_URL)

        val MATCHER =  UriMatcher(UriMatcher.NO_MATCH)
    }

    // тут смотри пример:
    // https://github.com/android/architecture-components-samples/blob/master/PersistenceContentProviderSample/app/src/main/java/com/example/android/contentprovidersample/provider/SampleContentProvider.java
    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query(selectTableName(uri), cols, condition, condition_val, null, null, order)
    }

    override fun getType(uri: Uri): String? {
        when (uri) {
            WORDS_CONTENT_URI ->  return "vnd.android.cursor.dir/vnd.nestdev.wordsSetTable"
            TRIALS_CONTENT_URI ->  return "vnd.android.cursor.dir/vnd.nestdev.resultsTable"
        }
       return null
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert(selectTableName(uri), null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete(selectTableName(uri), condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        val count: Int = db.update(selectTableName(uri), cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    private fun selectTableName(uri: Uri) : String {
        lateinit var tableName: String
        when (uri) {
            WORDS_CONTENT_URI -> tableName = "WORDS_SET_TABLE"
            TRIALS_CONTENT_URI -> tableName = "RESULTS_TABLE"
        }
        return tableName
    }
}
//закрыть базу данных открыть закрыть в каждой функции передачи данных, посмотреть рекомендации