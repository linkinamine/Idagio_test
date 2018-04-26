package idagio.amine.testapp.dependency.models

class Driver {

    fun enterCar(): String {
        return Const.DRIVER_IN
    }

    fun leaveCar(): String {
        return Const.DRIVER_OUT
    }
}