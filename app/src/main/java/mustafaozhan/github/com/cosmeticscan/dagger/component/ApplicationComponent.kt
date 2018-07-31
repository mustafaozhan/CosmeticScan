package mustafaozhan.github.com.cosmeticscan.dagger.component

import android.content.Context
import dagger.Component
import mustafaozhan.github.com.cosmeticscan.annotation.ApplicationContext
import mustafaozhan.github.com.cosmeticscan.dagger.module.ApplicationModule
import javax.inject.Singleton

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    @ApplicationContext
    fun context(): Context

    fun viewModelComponent(): ViewModelComponent
}