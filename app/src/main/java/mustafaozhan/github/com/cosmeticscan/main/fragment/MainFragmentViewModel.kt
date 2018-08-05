package mustafaozhan.github.com.cosmeticscan.main.fragment

import mustafaozhan.github.com.cosmeticscan.base.BaseViewModel
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.cosmeticscan.room.dao.IngredientDao
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class MainFragmentViewModel : BaseViewModel() {


    var firstRun: Boolean = true

    @Inject
    lateinit var ingredientDao: IngredientDao

    override fun inject() {
        viewModelComponent.inject(this)
    }

    fun getAllIngredients() {
        subscribeService(dataManager.getAll(),
                ::ingredientsDownloadSuccess, ::ingredientsDownloadFail)
    }

    private fun ingredientsDownloadFail(throwable: Throwable) {}

    private fun ingredientsDownloadSuccess(ingredientsResponse: MutableList<Ingredient>) {
        ingredientsResponse.forEach {
            ingredientDao.insertIngredient(it)
        }
    }

    fun savePreferences() {
        dataManager.persistFirstRun(firstRun)
    }

    fun loadPreferences() {
        firstRun = dataManager.loadFirstRun()
    }


}


