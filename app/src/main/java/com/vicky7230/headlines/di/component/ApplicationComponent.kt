package com.vicky7230.headlines.di.component

import com.vicky7230.headlines.HeadlinesApplication
import com.vicky7230.headlines.di.module.ActivityBindingModule
import com.vicky7230.headlines.di.module.ApplicationModule
import com.vicky7230.headlines.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(headlinesApplication: HeadlinesApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(headlinesApplication: HeadlinesApplication)

}