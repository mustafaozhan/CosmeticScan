package mustafaozhan.github.com.cosmeticscan.dagger.component

import dagger.Subcomponent
import mustafaozhan.github.com.cosmeticscan.annotation.PerViewModel
import mustafaozhan.github.com.cosmeticscan.camera.CameraFragmentViewModel
import mustafaozhan.github.com.cosmeticscan.dagger.module.ViewModelModule
import mustafaozhan.github.com.cosmeticscan.main.activity.MainActivityViewModel
import mustafaozhan.github.com.cosmeticscan.main.fragment.MainFragmentViewModel
import mustafaozhan.github.com.cosmeticscan.settings.SettingsFragmentViewModel
import mustafaozhan.github.com.cosmeticscan.manual.ManualFragmentViewModel

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@PerViewModel
@Subcomponent(modules = [(ViewModelModule::class)])
interface ViewModelComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun inject(mainFragmentViewModel: MainFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
    fun inject(cameraFragmentViewModel: CameraFragmentViewModel)
    fun inject(manualFragmentViewModel: ManualFragmentViewModel)

}