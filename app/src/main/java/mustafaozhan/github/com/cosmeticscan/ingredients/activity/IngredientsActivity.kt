package mustafaozhan.github.com.cosmeticscan.ingredients.activity

import android.os.Bundle
import android.view.WindowManager
import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.BaseMvvmActivity
import mustafaozhan.github.com.cosmeticscan.ingredients.fragment.IngredientsFragment

/**
 * Created by Mustafa Ozhan on 2018-08-05.
 */
class IngredientsActivity : BaseMvvmActivity<IngredientsActivityViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(IngredientsFragment.newInstance(intent.extras.getString(IngredientsFragment.INGREDIENTS)), false)

    }


    override fun getViewModelClass(): Class<IngredientsActivityViewModel> = IngredientsActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_ingredients


}