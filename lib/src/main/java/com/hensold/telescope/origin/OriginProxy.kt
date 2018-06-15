package com.hensold.telescope.origin

import android.os.IBinder
import android.os.Parcel
import android.os.RemoteException
import com.hensold.telescope.listener.IListener
import com.hensold.telescope.listener.ListenerStub

/**
 * Project: telescope
 * Created by z001lhn on 2/20/18.
 *
 * This is used in the remote observer to set up (subscribe) to
 * an observable located in another process
 */
class OriginProxy (val binder:IBinder):IOrigin{

    override fun subscribe(listener: IListener) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try{
            data.writeInterfaceToken(IOrigin.INTERFACE_ID)
            data.writeStrongBinder(ListenerStub(listener))

            binder.transact(IOrigin.TRANS_SUBSCRIBE,data,reply, IBinder.FLAG_ONEWAY)
        }catch (e:RemoteException){
            throw RuntimeException(e)
        }
        catch (e:Exception){
            e.printStackTrace()
        }finally {
            data.recycle()
            reply.recycle()
        }
    }

    override fun unsubscribe(listener: IListener) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try{
            data.writeInterfaceToken(IOrigin.INTERFACE_ID)
            data.writeStrongBinder(ListenerStub(listener))

            binder.transact(IOrigin.TRANS_UNSUBSCRIBE,data,reply, IBinder.FLAG_ONEWAY)
        }catch (e:RemoteException){
            throw RuntimeException(e)
        }
        catch (e:Exception){
            e.printStackTrace()
        }finally {
            data.recycle()
            reply.recycle()
        }
    }

}