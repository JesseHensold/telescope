package com.hensold.telescope

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import com.hensold.telescope.origin.OriginStub
import com.hensold.telescope.subscription.SubscriptionManager
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import timber.log.Timber

class DemoService : Service() {


    val testSubject : Subject<TestParcelable> = PublishSubject.create()
    val subManager = SubscriptionManager(testSubject)
    val observableStub = OriginStub(subManager)


    val testTimer = object:CountDownTimer(10000, 1000){
        override fun onFinish() {
            testSubject.onComplete()
        }

        override fun onTick(millisUntilFinished: Long) {
            testSubject.onNext(TestParcelable(testName = "TEST",
                    testData = mutableMapOf(Pair("time","$millisUntilFinished"))))
        }

    }
    override fun onCreate() {
        if(Timber.treeCount()<1) {
            Timber.plant(object : Timber.DebugTree() {
                override fun isLoggable(tag: String?, priority: Int) = true
            })
        }
        testTimer.start()
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
       return observableStub
    }
}
