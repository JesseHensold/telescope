package com.hensold.telescope.origin

import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import android.os.Parcel
import com.hensold.telescope.subscription.ISubscriptionManager
import timber.log.Timber

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 *
 * This exists in the origin process that is publishing the observable
 * and connects the remote listener to the local subject
 */
class OriginStub(val subscriptionManager: ISubscriptionManager): Binder() {

    val binderInterface: IInterface = IInterface { this@OriginStub }

    init{
        this.attachInterface(binderInterface, IOrigin.INTERFACE_ID)
    }

    override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
        Timber.v("onTransact : $code")
        try{
            when(code){
                IBinder.INTERFACE_TRANSACTION -> {
                    reply?.writeString(IOrigin.INTERFACE_ID)
                    return true
                }
                IOrigin.TRANS_SUBSCRIBE -> {
                    data?.let{
                        it.enforceInterface(IOrigin.INTERFACE_ID)
                        val binder = it.readStrongBinder()
                        subscriptionManager.subscribeBinder(binder)
                    }
                    return true
                }
                IOrigin.TRANS_UNSUBSCRIBE -> {
                    data?.let{
                        it.enforceInterface(IOrigin.INTERFACE_ID)
                        val binder = it.readStrongBinder()
                    }
                }
                else -> {
                    throw IllegalArgumentException("Illegal transaction code: $code")
                }
            }
        }catch(e:Exception){
            Timber.e(e)
            reply?.setDataPosition(0)
            reply?.writeException(e)
        }

        return super.onTransact(code, data, reply, flags)
    }
}