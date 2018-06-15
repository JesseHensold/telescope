package com.hensold.telescope

import android.os.Parcel
import android.os.Parcelable


/**
 * Project: telescope
 * Created by z001lhn on 2/21/18.
 */
interface BaseEvent : Parcelable {
    companion object {
        fun <T:BaseEvent> createFromParcel(parcel:Parcel):T? = null
    }
}