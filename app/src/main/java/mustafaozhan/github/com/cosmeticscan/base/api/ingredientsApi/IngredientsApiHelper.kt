package mustafaozhan.github.com.mycurrencies.base.api.exchangerates

import mustafaozhan.github.com.cosmeticscan.R
import mustafaozhan.github.com.cosmeticscan.base.api.ingredientsApi.IngredientsApiServices
import mustafaozhan.github.com.mycurrencies.base.api.BaseApiHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mustafa Ozhan on 2018-07-12.
 */
@Singleton
class IngredientsApiHelper @Inject
constructor() : BaseApiHelper() {

    companion object {
        const val TOKEN = ""
    }

    val ingredientsApiServices: IngredientsApiServices by lazy { initExchangeRatesApiServices() }

    private fun initExchangeRatesApiServices(): IngredientsApiServices {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor {
            it.proceed(createInterceptorRequest(it))
        }
        val endpoint = getString(R.string.exchange_rates_endpoint)
        val retrofit = initRxRetrofit(endpoint, clientBuilder.build())
        return retrofit.create(IngredientsApiServices::class.java)
    }

    private fun createInterceptorRequest(chain: Interceptor.Chain): Request {
        val original = chain.request()
        val builder = original.newBuilder()
//                .header("", TOKEN)
        return builder.build()
    }
}