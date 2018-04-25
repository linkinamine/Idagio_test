package idagio.amine.testapp.leak

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import idagio.amine.testapp.IdagioTestApplication
import idagio.amine.testapp.R
import kotlinx.android.synthetic.main.activity_leak.*
import java.lang.ref.WeakReference


class LeakActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leak)
        NonLeakyThread(am_leaky_tv, this).start()
    }

    //We will be using weak reference to avoid the leak more details on Readme file
    private class NonLeakyThread(textView: TextView, activity: Activity) : Thread() {

        var view: WeakReference<TextView> = WeakReference(textView)
        var activity: WeakReference<Activity> = WeakReference(activity)

        override fun run() {
            try {
                Thread.sleep(10000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val textView = view.get()
            val activity = activity.get()
            //We check if none is null meaning we still hold their reference
            if (textView != null && activity != null) {
                activity.runOnUiThread({ textView.text = "Hello" })
            }
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        IdagioTestApplication.getRefWatcher(this)?.watch(this);
    }
}