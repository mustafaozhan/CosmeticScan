package mustafaozhan.github.com.cosmeticscan.camera

import android.arch.lifecycle.MutableLiveData
import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class CameraFragmentViewModel : BaseViewModel() {

    var ingredients: MutableList<Ingredient> = mutableListOf()
    var foundedLiveData: MutableLiveData<String> = MutableLiveData()
    var counter = 0
    override fun inject() {
        viewModelComponent.inject(this)
    }

    @Inject
    lateinit var ingredientDao: IngredientDao


    fun getIngredients() {
        ingredients = ingredientDao.getAllIngredients()
    }

    fun search(text: String?) {
        ingredients.forEach {
            if (it.name == text.toString()) {
                counter++
            }
        }
    }

}
