package com.example.chris.architecturecomponent.util

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.annotation.Nonnull
import javax.annotation.Nullable

/**
 * Created by Chris on 1/10/18.
 */
class RxExecutor {
    fun <T> executeSingle(method: () -> T,
                          @Nonnull subscribeScheduler: Scheduler,
                          @Nullable observeScheduler: Scheduler = subscribeScheduler,
                          successful: (t: T) -> Unit,
                          error: (t: Throwable?) -> Unit) {

        Single.create<T> { t -> try { t.onSuccess(method()) } catch (e: Throwable) { t.onError(e) } }
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(
                        {t: T -> successful(t) },
                        {t: Throwable? ->  error(t)}
                )
    }
}