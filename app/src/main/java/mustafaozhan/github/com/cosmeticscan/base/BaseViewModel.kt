package mustafaozhan.github.com.cosmeticscan.base

import android.arch.lifecycle.ViewModel
import mustafaozhan.github.com.cosmeticscan.application.Application
import mustafaozhan.github.com.cosmeticscan.dagger.component.ViewModelComponent

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
abstract class BaseViewModel : ViewModel() {

    protected val viewModelComponent: ViewModelComponent by lazy { Application.instance.component.viewModelComponent() }


    init {
        inject()
    }

    protected abstract fun inject()


}