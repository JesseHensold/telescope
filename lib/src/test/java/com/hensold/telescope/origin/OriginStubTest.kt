package com.hensold.telescope.origin


import android.os.Parcel
import android.os.Parcelable
import com.hensold.telescope.listener.IListener
import com.hensold.telescope.origin.OriginStub
import com.hensold.telescope.subscription.ISubscriptionManager
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Project: telescope
 * Created by z001lhn on 2/20/18.
 */
@RunWith(RobolectricTestRunner::class)
class OriginStubTest{
    val subManager:ISubscriptionManager = mock()
    val stub = OriginStub(subManager)
    lateinit var data:Parcel
    lateinit var reply:Parcel

    @Before
    fun setup(){
        data= Parcel.obtain()
        reply = Parcel.obtain()
        data.writeInterfaceToken(IOrigin.INTERFACE_ID)
    }

    @After
    fun teardown(){
        data.recycle()
        reply.recycle()
    }

    @Test
    fun shouldHandleSubscribe(){
        stub.transact(IOrigin.TRANS_SUBSCRIBE,data,reply,0)

        verify(subManager).subscribeBinder(any())
    }
}