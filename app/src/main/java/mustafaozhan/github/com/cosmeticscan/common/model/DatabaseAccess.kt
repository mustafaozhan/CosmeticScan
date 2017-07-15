package mustafaozhan.github.com.cosmeticscan.common.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.android.gms.tasks.Task




/**
Created by Mustafa Özhan on 7/15/17 at 1:24 PM on Linux.

 */
class DatabaseAccess
private constructor(context: Context) {
    private val openHelper: SQLiteOpenHelper
    val TABLE_NAME="Ingredients"
    private var database: SQLiteDatabase? = null
    companion object {
        private var instance: DatabaseAccess? = null

        fun getInstance(context: Context): DatabaseAccess {
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance as DatabaseAccess
        }
    }
    init {
        this.openHelper = DatabaseOpenHelper(context)
    }

    fun open() {
        this.database = openHelper.writableDatabase
    }

    fun close() {
        if (database != null) {
            this.database!!.close()
        }
    }
    val ingredients: ArrayList<Ingredient>? = null

    fun getAllIngredients(): ArrayList<Ingredient>? {

        val cursor = database!!.rawQuery("SELECT * FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            ingredients?.add(Ingredient(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)))
            cursor.moveToNext()

        }
        cursor.close()
        return ingredients!!
    }

    fun getAllTasks(): ArrayList<Ingredient>? {

//       val db = open()

        val cursor = database!!.query(TABLE_NAME, arrayOf("id", "name","information","rating","categories"), null, null, null, null, null)
        while (cursor.moveToNext()) {
            val task = Ingredient()
            task.id=cursor.getInt(0)
            task.name=cursor.getString(1)
            task.information=cursor.getString(2)
            task.rating=cursor.getString(3)
            task.categories=cursor.getString(4)




            ingredients?.add(task)
        }

        return ingredients
    }


}