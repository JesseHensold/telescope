package com.hensold.telescope

import android.os.Parcel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Project: telescope
 * Created by z001lhn on 3/1/18.
 */
@RunWith(RobolectricTestRunner::class)
class RemoteSubjectTest {

    var data: Parcel?=null
    @Before
    fun setup(){
        data= Parcel.obtain()
    }

    @After
    fun teardown(){
        data?.recycle()
    }

    @Test
    fun shouldExtractTypedData() {
        val strRemoteSubject = RemoteSubject<String>(RuntimeEnvironment.application.applicationContext)
        data?.writeString("TESTSTRING")

    }
}