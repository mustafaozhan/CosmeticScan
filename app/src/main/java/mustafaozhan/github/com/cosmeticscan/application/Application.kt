package mustafaozhan.github.com.cosmeticscan.application

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.BuildConfig
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
class Application : android.app.Application() {
    //For supporting multidex before android 5
    companion object {
        lateinit var instance: Application

        fun get(context: Context): Application {
            return context.applicationContext as Application
        }
    }

    override fun onCreate() {
        super.onCreate()

        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        Fabric.with(this, Crashlytics.Builder().core(core).build())

        instance = this
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder() // will be auto generated after build
                .applicationModule(ApplicationModule(this)).build()
    }

}