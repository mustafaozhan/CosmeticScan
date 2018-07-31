package mustafaozhan.github.com.cosmeticscan.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@Dao
abstract class IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredient")
    abstract fun getAllIngredients(): MutableList<Ingredient>

}