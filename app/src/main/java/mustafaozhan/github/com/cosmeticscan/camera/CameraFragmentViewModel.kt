package mustafaozhan.github.com.cosmeticscan.camera

import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class CameraFragmentViewModel : BaseViewModel() {

    var ingredients: MutableList<Ingredient> = mutableListOf()

    override fun inject() {
        viewModelComponent.inject(this)
    }

    @Inject
    lateinit var ingredientDao: IngredientDao


    fun getIngredients() {
        ingredients = ingredientDao.getAllIngredients()
    }

}
