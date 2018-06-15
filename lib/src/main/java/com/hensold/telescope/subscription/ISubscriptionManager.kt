package com.hensold.telescope.subscription

import android.os.IBinder
import android.os.Parcel

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
interface ISubscriptionManager {

    fun subscribeBinder(binder:IBinder)
}