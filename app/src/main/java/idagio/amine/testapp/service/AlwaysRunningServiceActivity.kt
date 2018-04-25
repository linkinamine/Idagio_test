package idagio.amine.testapp.service

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import idagio.amine.testapp.R
import kotlinx.android.synthetic.main.activity_service.*


class AlwaysRunningServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        initializeListeners()
    }

    private fun initializeListeners() {
        as_run_btn.setOnClickListener {
            val startIntent = Intent(this, AlwaysRunningService::class.java)
            startIntent.action = Constants.STARTFOREGROUND_ACTION
            startService(startIntent)
        }
        as_stop_btn.setOnClickListener {
            val startIntent = Intent(this, AlwaysRunningService::class.java)
            startIntent.action = Constants.STOPFOREGROUND_ACTION
            startService(startIntent)
        }
    }
}