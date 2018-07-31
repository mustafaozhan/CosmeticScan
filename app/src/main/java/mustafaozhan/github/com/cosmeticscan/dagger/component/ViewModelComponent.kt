package mustafaozhan.github.com.cosmeticscan.dagger.component

import dagger.Subcomponent
import mustafaozhan.github.com.cosmeticscan.annotation.PerViewModel
import mustafaozhan.github.com.cosmeticscan.dagger.module.ViewModelModule

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@PerViewModel
@Subcomponent(modules = [(ViewModelModule::class)])
interface ViewModelComponent {

}