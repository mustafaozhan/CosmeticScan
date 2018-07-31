package mustafaozhan.github.com.cosmeticscan.base

import android.arch.lifecycle.ViewModelProviders

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
abstract class BaseMvvmFragment<VM : BaseViewModel> : BaseFragment() {

    protected abstract fun getViewModelClass(): Class<VM>

    protected val viewModel: VM by lazy { ViewModelProviders.of(this).get(getViewModelClass()) }
}