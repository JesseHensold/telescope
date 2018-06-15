package com.hensold.telescope.listener

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.*
import com.hensold.telescope.BaseEvent
import timber.log.Timber

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
class ListenerStub(val listener:IListener)
    : Binder() {

    val binderInterface: IInterface = IInterface { this@ListenerStub }

    init {
        this.attachInterface(binderInterface, IListener.INTERFACE_ID)
    }

    override fun onTransact(code: Int, data: Parcel, reply: Parcel, flags: Int): Boolean {
        try {
            when(code){
                IBinder.INTERFACE_TRANSACTION -> {
                    reply.writeString(IListener.INTERFACE_ID)
                    return true
                }
                IListener.TRANS_NEXT -> {
                    data.enforceInterface(IListener.INTERFACE_ID)
                    listener.onNext(data)
                    return true
                }
                IListener.TRANS_ERROR -> {
                    data.enforceInterface(IListener.INTERFACE_ID)
                    return true
                }
                IListener.TRANS_COMPLETE -> {
                    data.enforceInterface(IListener.INTERFACE_ID)
                    listener.onCompleted()
                    return true
                }
                IListener.TRANS_MESSAGE -> {
                    data.enforceInterface(IListener.INTERFACE_ID)
                    listener.onMessage(data.readString())
                    return true
                }

            }
        } catch (e: Exception) {
            Timber.e(e)
            listener.onError(e)
        }

        return super.onTransact(code, data, reply, flags)
    }

}