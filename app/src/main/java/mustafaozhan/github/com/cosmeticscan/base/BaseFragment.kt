package mustafaozhan.github.com.cosmeticscan.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mustafaozhan.github.com.cosmeticscan.R

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
abstract class BaseFragment : Fragment() {

    val fragmentTag: String = this.javaClass.simpleName

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    @MenuRes
    open var menuResID: Int? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//    protected fun initToolbar() {
//        when (getLayoutResId()) {
//            R.layout.fragment_main -> getBaseActivity().setSupportActionBar(fragment_main_toolbar)
//            R.layout.fragment_settings -> getBaseActivity().setSupportActionBar(fragment_settings_toolbar)
//        }
//    }

    protected fun getBaseActivity(): BaseActivity = activity as BaseActivity


}