package mustafaozhan.github.com.cosmeticscan.common.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
Created by Mustafa Özhan on 7/20/17 at 8:19 PM on Linux <3.

 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        const val TABLE_NAME = "ingredients"
        const val ID = "id"
        const val NAME = "name"
        const val INFORMATION = "information"
        const val CATEGORY = "category"
        const val RATING = "rating"


        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
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
        // Here you can upgrade tables, as usual
        db.dropTable(TABLE_NAME, true)
    }

    fun insertIngredient(ingredient: Ingredient) {
        instance!!.use {

            insert(
                    TABLE_NAME,
                    ID to ingredient.id,
                    NAME to ingredient.name,
                    INFORMATION to ingredient.information,
                    CATEGORY to ingredient.category,
                    RATING to ingredient.rating
            )
        }
    }

    fun insertIngredientList(ingredientList: List<Ingredient>) {
        instance!!.use {
            for (i in 0..ingredientList.size - 1)
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


}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)