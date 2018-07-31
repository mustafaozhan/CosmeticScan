package mustafaozhan.github.com.mycurrencies.tools

import io.reactivex.Observable
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import mustafaozhan.github.com.mycurrencies.base.api.exchangerates.IngredientsApiHelper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mustafa Ozhan on 7/10/18 at 9:50 PM on Arch Linux wit Love <3.
 */
@Singleton
class DataManager @Inject
constructor(private val generalSharedPreferences: GeneralSharedPreferences) {

    @Inject
    lateinit var ingredientsApiHelper: IngredientsApiHelper

    fun getAll(): Observable<MutableList<Ingredient>> =
            ingredientsApiHelper.ingredientsApiServices.getAll()

    fun loadFirstRun() = generalSharedPreferences.loadFirstRun()

    fun persistFirstRun(firstRun: Boolean) {
        generalSharedPreferences.persistFirstRun(firstRun)
    }

}