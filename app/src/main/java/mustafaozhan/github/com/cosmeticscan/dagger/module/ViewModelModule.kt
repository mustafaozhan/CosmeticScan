package mustafaozhan.github.com.cosmeticscan.dagger.module

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@Module
class ViewModelModule(private val viewModel: ViewModel) {

    @Provides
    internal fun providesViewModel(): ViewModel {
        return viewModel
    }
}