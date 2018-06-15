package com.hensold.telescope.origin

import android.os.IBinder
import com.hensold.telescope.listener.IListener

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
interface IOrigin {

    companion object {
        const val INTERFACE_ID = "com.target.acorn.Origin"
        const val TRANS_SUBSCRIBE = IBinder.FIRST_CALL_TRANSACTION+1
        const val TRANS_UNSUBSCRIBE = IBinder.FIRST_CALL_TRANSACTION+2
    }

    fun subscribe(listener:IListener)
    fun unsubscribe(listener: IListener)
}
