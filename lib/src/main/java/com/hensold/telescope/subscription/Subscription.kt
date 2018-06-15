package com.hensold.telescope.subscription

import android.os.Binder
import android.os.IBinder
import android.os.Parcelable
import com.hensold.telescope.listener.ListenerProxy
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject

/**
 * Project: telescope
 * Created by z001lhn on 2/20/18.
 */
class Subscription<T:Parcelable>(binder: IBinder,
                   subject: Subject<T>){

    val pid = Binder.getCallingPid()
    val listener = ListenerProxy(binder)

    val deathRecipient = IBinder.DeathRecipient { disposable?.dispose() }

    var disposable:Disposable?=null
    init{
        binder.linkToDeath(deathRecipient, 0)

        disposable = subject.observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = listener::onNext,
                        onError = listener::onError,
                        onComplete = listener::onCompleted
                )
    }
}