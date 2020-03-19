package com.valuekart.bloodbank.dependencyInjection.component

import android.app.Application
import com.valuekart.bloodbank.BloodBankApp
import com.valuekart.bloodbank.dependencyInjection.builder.ActivityBuilder
import com.valuekart.bloodbank.dependencyInjection.builder.ServicesBuilder
import com.valuekart.bloodbank.dependencyInjection.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),(AppModule::class),(ActivityBuilder::class),(ServicesBuilder::class),AndroidSupportInjectionModule::class])
interface AppComponent {
    fun inject(app: BloodBankApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}