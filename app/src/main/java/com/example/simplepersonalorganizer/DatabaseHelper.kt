package com.example.simplepersonalorganizer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "UserDB", null, 1) {

    private val tableName = "Users"

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $tableName (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                email TEXT
            );
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    fun insertUser(name: String, email: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
        }

        val result = db.insert(tableName, null, values)
        return result != -1L
    }

    //fun getAllUsers(): List<Pair<String, String>> {
       // val db = this.readableDatabase
       // val cursor = db.rawQuery("SELECT name, email FROM $tableName", null)
      //  val result = mutableListOf<Pair<String, String>>()

      //  while (cursor.moveToNext()) {
       //     val name = cursor.getString(0)
       //     val email = cursor.getString(1)
         //   result.add(name to email)
      //  }

       // cursor.close()
     //   return result
  //  }
    fun getAdminUsers(): List<Pair<String, String>> {
        val db   = readableDatabase
        val list = mutableListOf<Pair<String, String>>()
        val c = db.rawQuery(
            "SELECT name, email FROM $tableName WHERE name = ?",
            arrayOf("admin")
        )
        c.use {
            while (it.moveToNext()) {
                list += it.getString(0) to it.getString(1)
            }
        }
        return list
    }


}
