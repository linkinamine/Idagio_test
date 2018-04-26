package idagio.amine.testapp.dependency.di

import dagger.Component
import idagio.amine.testapp.dependency.DependencyInjectionActivity
import idagio.amine.testapp.dependency.models.Car
import javax.inject.Singleton

@Singleton
@Component(modules = [(MyModule::class)])
interface MyComponent {
    fun provideCar(): Car
    fun inject(main: DependencyInjectionActivity)
}
