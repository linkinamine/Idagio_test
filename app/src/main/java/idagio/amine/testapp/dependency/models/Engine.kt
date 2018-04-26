package idagio.amine.testapp.dependency.models

class Engine {

    fun start(): String {
        return Const.CAR_RUNNING
    }

    fun stop(): String {
        return Const.CAR_STOPPED
    }
}