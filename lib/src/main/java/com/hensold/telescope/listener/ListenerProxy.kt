package com.hensold.telescope.listener

import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
class ListenerProxy(val binder:IBinder):IListener {

    override fun onNext(t: Any) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try{
            data.writeInterfaceToken(IListener.INTERFACE_ID)
                data.writeInt(1)
                addToParcel(t, data)
            binder.transact(IListener.TRANS_NEXT,data,reply, IBinder.FLAG_ONEWAY)
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            data.recycle()
            reply.recycle()
        }
    }

    override fun onCompleted() {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try{
            data.writeInterfaceToken(IListener.INTERFACE_ID)
            binder.transact(IListener.TRANS_COMPLETE,data,reply, IBinder.FLAG_ONEWAY)
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            data.recycle()
            reply.recycle()
        }
    }


    override fun onError(throwable: Throwable) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try{
            data.writeInterfaceToken(IListener.INTERFACE_ID)
            binder.transact(IListener.TRANS_ERROR,data,reply, IBinder.FLAG_ONEWAY)
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            data.recycle()
            reply.recycle()
        }
    }

    override fun onMessage(message: String) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try{
            data.writeInterfaceToken(IListener.INTERFACE_ID)
            data.writeString(message)
            binder.transact(IListener.TRANS_MESSAGE,data,reply, IBinder.FLAG_ONEWAY)
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            data.recycle()
            reply.recycle()
        }
    }


    fun addToParcel(t:Any, data:Parcel){
        when(t){
            is Parcelable -> t.writeToParcel(data,0)
            is String -> data.writeString(t)
            else -> {}
        }
    }
}