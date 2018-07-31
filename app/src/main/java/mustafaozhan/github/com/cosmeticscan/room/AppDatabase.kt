package mustafaozhan.github.com.cosmeticscan.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@Database(entities = [(Ingredient::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ingredientsDao(): IngredientDao


}