package mocks

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.hensold.telescope.origin.IObservableAdapter

/**
 * Project: telescope
 * Created by z001lhn on 2/20/18.
 */
class MockService:Service(){


    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented")
    }

}