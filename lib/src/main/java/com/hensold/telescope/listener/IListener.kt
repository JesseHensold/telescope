package com.hensold.telescope.listener

import android.os.IBinder
import android.os.Parcel

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
interface IListener{
    companion object {
        const val INTERFACE_ID = "com.hensold.telescope.Listener"
        const val TRANS_NEXT = IBinder.FIRST_CALL_TRANSACTION+0
        const val TRANS_ERROR = IBinder.FIRST_CALL_TRANSACTION+1
        const val TRANS_COMPLETE = IBinder.FIRST_CALL_TRANSACTION+2
        const val TRANS_MESSAGE = IBinder.FIRST_CALL_TRANSACTION+3
    }

    fun onCompleted()
    fun onNext(t:Any)
    fun onError(throwable:Throwable)
    fun onMessage(message:String)
}