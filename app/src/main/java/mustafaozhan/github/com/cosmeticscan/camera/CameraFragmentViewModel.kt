package mustafaozhan.github.com.cosmeticscan.camera

import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
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

    var foundedList: ArrayList<String> = ArrayList<String>()


    fun searchForIngredients(foundedText: String) {

        ingredients.forEach {
            if (foundedText.contains(it.name)) {
                if (!foundedList.contains(it.name)) {
                    foundedList.add(it.name)
                }
            }
        }

    }

    fun refresh() {
        foundedList.forEach { foundedList.remove(it) }
    }

}
