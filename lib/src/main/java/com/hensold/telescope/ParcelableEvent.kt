package com.hensold.telescope

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Project: telescope
 * Created by z001lhn on 2/21/18.
 */
@SuppressLint("ParcelCreator")
@Parcelize
class ParcelableEvent(val testy:String) : Parcelable