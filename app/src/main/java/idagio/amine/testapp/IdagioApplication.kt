package idagio.amine.testapp

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.RefWatcher


open class IdagioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)*/
    }


    private val refWatcher: RefWatcher? = null

    companion object {
        fun getRefWatcher(context: Context): RefWatcher? {
            val application = context.applicationContext as IdagioApplication
            if (application.refWatcher != null)
                return application.refWatcher
            return null
        }
    }
}