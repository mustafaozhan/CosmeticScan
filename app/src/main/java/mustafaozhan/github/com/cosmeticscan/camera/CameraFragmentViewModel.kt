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


    override fun inject() {
        viewModelComponent.inject(this)
    }

    @Inject
    lateinit var ingredients: MutableList<Ingredient>

    var foundedIngredientsLiveData: MutableLiveData<String> = MutableLiveData()


    fun searchForIngredients(foundedText: String) {

        ingredients.forEach {
            if (foundedText.contains(it.name))
                foundedIngredientsLiveData.postValue(it.name)
        }

    }

}
