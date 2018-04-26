package idagio.amine.testapp.dependency.models

import javax.inject.Inject


class Car(private var engine: Engine, private var driver: Driver) {

    @Inject
    fun Car(engine: Engine, driver: Driver) {
        this.engine = engine
        this.driver = driver
    }

    fun start(): String {
        return driver.enterCar() + " " + engine.start()
    }

    fun stop(): String {
        return engine.stop() + " " + driver.leaveCar()
    }

}