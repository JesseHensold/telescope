package com.hensold.telescope.origin

import android.os.Parcel
import android.os.Parcelable

/**
 * Project: telescope
 * Created by z001lhn on 2/19/18.
 */
class MockParcelable(val name:String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MockParcelable> {
        override fun createFromParcel(parcel: Parcel): MockParcelable {
            return MockParcelable(parcel)
        }

        override fun newArray(size: Int): Array<MockParcelable?> {
            return arrayOfNulls(size)
        }
    }
}