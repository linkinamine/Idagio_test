package idagio.amine.testapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import idagio.amine.testapp.leak.LeakActivity
import idagio.amine.testapp.service.AlwaysRunningServiceActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeListeners()
    }

    private fun initializeListeners() {
        am_leak_btn.setOnClickListener {
            startActivity(Intent(applicationContext, LeakActivity::class.java))
        }
        am_service_btn.setOnClickListener {
            startActivity(Intent(applicationContext, AlwaysRunningServiceActivity::class.java))
        }
    }
}
