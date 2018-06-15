package com.hensold.telescope.subscription

import android.os.IBinder
import android.os.Parcelable
import io.reactivex.subjects.Subject

/**
 * Project: telescope
 * Created by z001lhn on 2/13/18.
 */
class SubscriptionManager(val subject: Subject<out Parcelable>): ISubscriptionManager{


    override fun subscribeBinder(binder: IBinder) {
        if(binder.isBinderAlive) {
            com.hensold.telescope.subscription.Subscription(binder, subject)
        }
    }
}