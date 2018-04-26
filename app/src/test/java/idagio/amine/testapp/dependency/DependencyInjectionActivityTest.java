package idagio.amine.testapp.dependency;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import idagio.amine.testapp.R;
import idagio.amine.testapp.dependency.models.Car;
import idagio.amine.testapp.dependency.models.Const;
import idagio.amine.testapp.dependency.models.Driver;
import idagio.amine.testapp.dependency.models.Engine;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class DependencyInjectionActivityTest {

    private DependencyInjectionActivity dependencyInjectionActivity;
    private Car car;

    @Before
    public void setUp() throws Exception {
        dependencyInjectionActivity = Robolectric.setupActivity(DependencyInjectionActivity.class);
        car = new Car(new Engine(), new Driver());
    }

    @Test
    public void stopCarIsSuccessful() throws Exception {
        TextView carStopped = dependencyInjectionActivity.findViewById(R.id.ad_car_state_tv);
        carStopped.setText(car.stop());
        assertEquals(Const.CAR_STOPPED + " " + Const.DRIVER_OUT, carStopped.getText());
    }

    @Test
    public void startCarIsSuccessful() throws Exception {
        car.start();
        TextView carRunning = dependencyInjectionActivity.findViewById(R.id.ad_car_state_tv);
        assertEquals(Const.DRIVER_IN + " " + Const.CAR_RUNNING, carRunning.getText());
    }

}
