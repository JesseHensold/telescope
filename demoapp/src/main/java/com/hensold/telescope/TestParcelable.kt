package com.hensold.telescope

import android.os.Parcel
import android.os.Parcelable

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
class TestParcelable(val testName:String,
                     val testData: MutableMap<String,String>?=null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("testData")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(testName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestParcelable> {
        override fun createFromParcel(parcel: Parcel): TestParcelable {
            return TestParcelable(parcel)
        }

        override fun newArray(size: Int): Array<TestParcelable?> {
            return arrayOfNulls(size)
        }
    }
}