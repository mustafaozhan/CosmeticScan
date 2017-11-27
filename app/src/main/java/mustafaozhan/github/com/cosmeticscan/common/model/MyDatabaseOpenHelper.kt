package mustafaozhan.github.com.cosmeticscan.common.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
Created by Mustafa Özhan on 7/20/17 at 8:19 PM on Linux <3.

 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    private val parser = rowParser { id: Int, name: String, information: String, category: String, rating: String ->
        Ingredient(id, name, information, category, rating)
    }

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        const val TABLE_NAME = "ingredients"
        const val ID = "id"
        const val NAME = "name"
        const val INFORMATION = "information"
        const val CATEGORY = "category"
        const val RATING = "rating"
        var myList: List<String>? = null


        @Synchronized
        fun getInstance(ctx: Context?): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = ctx?.applicationContext?.let { MyDatabaseOpenHelper(it) }
                myList = instance!!.use {
                    select(TABLE_NAME)
                            .column(NAME)
                            .exec { parseList(StringParser) }

                }
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(TABLE_NAME, true,
                ID to INTEGER + PRIMARY_KEY,
                NAME to TEXT,
                INFORMATION to TEXT,
                CATEGORY to TEXT,
                RATING to TEXT)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.dropTable(TABLE_NAME, true)
    }

    fun insertIngredientList(ingredientList: List<Ingredient>) {
        instance!!.use {
            for (i in 0 until ingredientList.size)
                insert(
                        TABLE_NAME,
                        ID to ingredientList[i].id,
                        NAME to ingredientList[i].name,
                        INFORMATION to ingredientList[i].information,
                        CATEGORY to ingredientList[i].category,
                        RATING to ingredientList[i].rating
                )
        }
    }


    fun searchInDatabase(fromCamera: String, alreadyHave: String): String? {
        var result = ""

        (0 until myList!!.size)
                .asSequence()
                .filter { fromCamera.contains(myList!![it], ignoreCase = true) && !alreadyHave.contains(myList!![it]) }
                .forEach { result = myList!![it] + "," + result }
        return (result + alreadyHave)
    }

    fun getIngredientsByName(names: List<String>): ArrayList<Ingredient> {


        val resultList = ArrayList<Ingredient>()
        val tempList = use {
            select(TABLE_NAME)
                    .exec { parseList(parser) }

        }

        for (i in 0 until names.size)
            (0 until tempList.size)
                    .filter { names[i].equals(tempList[it].name.toString(), ignoreCase = true) }
                    .mapTo(resultList) { tempList[it] }

        return resultList

    }

    fun getMatchByName(text: String?): ArrayList<Ingredient>? {

        val resultList = ArrayList<Ingredient>()
        val tempList = use {
            select(TABLE_NAME)
                    .exec { parseList(parser) }

        }

        (0 until tempList.size)
                .filter { tempList[it].name!!.contains(text.toString(), ignoreCase = true) }
                .mapTo(resultList) { tempList[it] }

        return resultList
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)