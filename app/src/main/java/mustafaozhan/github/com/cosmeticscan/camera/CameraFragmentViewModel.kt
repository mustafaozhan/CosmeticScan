package mustafaozhan.github.com.cosmeticscan.camera

import android.arch.lifecycle.MutableLiveData
import android.util.SparseArray
import com.google.android.gms.vision.text.TextBlock
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

    var foundedList: ArrayList<String> = ArrayList()

    val foundedListLiveData: MutableLiveData<MutableList<String>> = MutableLiveData()

    fun searchForIngredients(detectedItems: SparseArray<TextBlock>) {

        if (detectedItems.size() != 0) {

            val stringBuilder = StringBuilder()
            for (i in 0 until detectedItems.size()) {
                val item = detectedItems.valueAt(i)
                stringBuilder.append(item.value)
            }

            ingredients.forEach {
                if (stringBuilder.toString().contains(it.name)) {


                    if (!foundedList.contains(it.name)) {
                        foundedList.add(it.name)
                        foundedListLiveData.postValue(foundedList)
                    }
                }
            }
        }
    }

    fun refresh() {
        for (i in 0 until foundedList.size) {
            foundedList.removeAt(0)
        }
    }

    fun foundedListToString(): String {
        var string = ""
        foundedList.forEach {
            string = "$string$it*"
        }
        return string
    }


}
