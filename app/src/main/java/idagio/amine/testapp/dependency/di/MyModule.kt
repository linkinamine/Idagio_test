package idagio.amine.testapp.dependency.di

import dagger.Module
import dagger.Provides
import idagio.amine.testapp.dependency.models.Car
import idagio.amine.testapp.dependency.models.Driver
import idagio.amine.testapp.dependency.models.Engine
import javax.inject.Singleton


@Module
class MyModule {

    @Provides
    @Singleton
    fun provideEngine(): Engine {
        return Engine()
    }

    @Provides
    @Singleton
    fun provideDriver(): Driver {
        return Driver()
    }

    @Provides
    @Singleton
    fun provideCar(): Car {
        return Car(Engine(), Driver())
    }
}