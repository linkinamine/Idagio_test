package idagio.amine.testapp.dependency

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import idagio.amine.testapp.R
import idagio.amine.testapp.dependency.di.DaggerMyComponent
import idagio.amine.testapp.dependency.models.Car
import kotlinx.android.synthetic.main.activity_dependency.*
import javax.inject.Inject


class DependencyInjectionActivity : AppCompatActivity() {

    var car: Car? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dependency)

        val component = DaggerMyComponent.builder().build()
        component.inject(this)

        ad_car_state_tv.text = car?.start()

        Handler().postDelayed({ ad_car_state_tv.text = car?.stop() }, 2000)
    }
}