package mustafaozhan.github.com.cosmeticscan.manual

import android.arch.lifecycle.MutableLiveData
import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class ManualFragmentViewModel : BaseViewModel() {

    var ingredients: MutableList<Ingredient> = mutableListOf()

    @Inject
    lateinit var ingredientDao: IngredientDao

    override fun inject() {
        viewModelComponent.inject(this)
    }

    fun getIngredients() {
        ingredients = ingredientDao.getAllIngredients()
    }


}
