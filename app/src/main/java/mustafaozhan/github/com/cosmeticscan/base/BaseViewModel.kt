package mustafaozhan.github.com.cosmeticscan.base

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import mustafaozhan.github.com.cosmeticscan.app.Application
import mustafaozhan.github.com.cosmeticscan.dagger.component.ViewModelComponent
import mustafaozhan.github.com.cosmeticscan.tools.applySchedulers
import mustafaozhan.github.com.mycurrencies.tools.DataManager
import javax.inject.Inject

/**
 * Created by Mustafa Ozhan on 2018-07-31.
 */
abstract class BaseViewModel : ViewModel() {

    protected val viewModelComponent: ViewModelComponent by lazy { Application.instance.component.viewModelComponent() }

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var dataManager: DataManager

    init {
        inject()
    }

    protected abstract fun inject()
    protected fun <T> subscribeService(serviceObservable: Observable<T>, onNext: (T) -> Unit,
                                       onError: (Throwable) -> Unit, onComplete: () -> Unit) {
        compositeDisposable.add(serviceObservable.applySchedulers().subscribe(onNext, onError, onComplete))
    }

    protected fun <T> subscribeService(serviceObservable: Observable<T>, onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
        compositeDisposable.add(serviceObservable.applySchedulers().subscribe(onNext, onError))
    }

    protected fun <T> subscribeService(serviceSingle: Single<T>, onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        compositeDisposable.add(serviceSingle.applySchedulers().subscribe(onSuccess, onError))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}