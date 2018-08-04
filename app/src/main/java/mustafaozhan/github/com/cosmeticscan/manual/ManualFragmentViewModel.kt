package mustafaozhan.github.com.cosmeticscan.manual

import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class ManualFragmentViewModel : BaseViewModel() {

    @Inject
    lateinit var ingredients: MutableList<Ingredient>

    override fun inject() {
        viewModelComponent.inject(this)
    }


}
