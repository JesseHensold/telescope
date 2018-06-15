package com.hensold.telescope

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Parcelable
import com.hensold.telescope.listener.IListener
import com.hensold.telescope.origin.OriginProxy
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * Project: telescope
 * Created by z001lhn on 2/20/18.
 */
class RemoteSubject<T>(val context: Context):IListener{

    companion object {
        private const val servicePackage = "com.hensold.telescope"
        private const val serviceClass = "com.hensold.telescope.DemoService"
        private val serviceIntent = Intent().setClassName(servicePackage, serviceClass)
    }

    private val serviceConnection = object:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            subject.onComplete()
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Timber.d("ServiceConnection establised to $name")
            originProxy = OriginProxy(service)
            subscribeListener()
        }

    }

    val subject: PublishSubject<T> = PublishSubject.create()

    var originProxy:OriginProxy? = null

    fun asObservable():Flowable<T> = subject.toFlowable(BackpressureStrategy.BUFFER)

    fun connectToOrigin(){
        Timber.d("Connecting to service")
        context.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun subscribeListener(){
        originProxy?.subscribe(this)
    }

    inline fun<reified TT> handleTypedData(data:Any){
        if(data is TT){
            subject.onNext(data as TT)
        }
    }

    override fun onCompleted() {
        subject.onComplete()
    }

    override fun onNext(t: Any) {
        handleTypedData<Parcelable>(t)
    }

    override fun onError(throwable: Throwable) {
        subject.onError(throwable)
    }

    override fun onMessage(message: String) {
        TODO("not implemented")
    }
}