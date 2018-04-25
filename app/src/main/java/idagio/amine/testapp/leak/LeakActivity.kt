package idagio.amine.testapp.leak

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import idagio.amine.testapp.IdagioTestApplication
import idagio.amine.testapp.R
import kotlinx.android.synthetic.main.activity_leak.*


class LeakActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leak)
        NonLeakyThread(am_leaky_tv, this).start()
    }

    private class NonLeakyThread(var view: TextView, var activity: Activity) : Thread() {

        override fun run() {
            try {
                Thread.sleep(10000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            activity.runOnUiThread { view.text = "Hello" }
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        IdagioTestApplication.getRefWatcher(this)?.watch(this);
    }
}