package mustafaozhan.github.com.cosmeticscan.base.api.ingredientsApi

import io.reactivex.Observable
import mustafaozhan.github.com.cosmeticscan.base.model.Ingredient
import retrofit2.http.GET

/**
 * Created by Mustafa Ozhan on 2018-07-12.
 */
interface IngredientsApiServices {
    @GET("Database.json")
    fun getAll(): Observable<MutableList<Ingredient>>
}