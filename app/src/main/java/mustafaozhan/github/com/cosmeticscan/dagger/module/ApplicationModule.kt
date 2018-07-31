package mustafaozhan.github.com.cosmeticscan.dagger.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.cosmeticscan.annotation.ApplicationContext
import mustafaozhan.github.com.cosmeticscan.application.Application
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application.applicationContext
    }


}