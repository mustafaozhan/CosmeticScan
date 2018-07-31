package mustafaozhan.github.com.cosmeticscan.annotation

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerViewModel