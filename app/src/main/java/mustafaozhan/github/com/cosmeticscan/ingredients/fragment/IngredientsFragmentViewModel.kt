package mustafaozhan.github.com.cosmeticscan.ingredients.fragment

import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-08-04.
 */
class IngredientsFragmentViewModel : BaseViewModel() {

    override fun inject() {
        viewModelComponent.inject(this)
    }

    var ingredients: MutableList<Ingredient> = mutableListOf()


    fun getData() {
        ingredientsName?.forEach {
            ingredients.add(ingredientDao.getIngredientsByName(it))
        }
    }



    @Inject
    lateinit var ingredientDao: IngredientDao
    var ingredientsName: List<String>? = ArrayList()



}