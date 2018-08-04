package mustafaozhan.github.com.cosmeticscan.dagger.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.cosmeticscan.annotation.ApplicationContext
import mustafaozhan.github.com.cosmeticscan.application.Application
import mustafaozhan.github.com.cosmeticscan.room.AppDatabase
import mustafaozhan.github.com.cosmeticscan.room.AppExecutors
import mustafaozhan.github.com.cosmeticscan.room.AppExecutors.Companion.THREAD_COUNT
import mustafaozhan.github.com.cosmeticscan.room.DiskIOThreadExecutor
import mustafaozhan.github.com.cosmeticscan.room.MainThreadExecutor
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


    @Provides
    @Singleton
    internal fun provideAppDatabase(): AppDatabase =
            Room.databaseBuilder(application, AppDatabase::class.java, "app_db").allowMainThreadQueries().build()

    @Provides
    @Singleton
    internal fun ingredientDao(database: AppDatabase) = database.ingredientsDao()


    @Provides
    @Singleton
    internal fun ingredients(database: AppDatabase) = database.ingredientsDao().getAllIngredients()


    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors(DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                MainThreadExecutor())
    }

}