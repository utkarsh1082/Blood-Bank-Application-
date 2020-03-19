package com.valuekart.bloodbank.dependencyInjection.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.local.db.address.AddressHelper
import com.valuekart.bloodbank.data.local.db.address.AddressHelperImpl
import com.valuekart.bloodbank.data.local.db.bloodBank.BloodBankHelper
import com.valuekart.bloodbank.data.local.db.bloodBank.BloodBankHelperImpl
import com.valuekart.bloodbank.dependencyInjection.ApplicationContext
import com.valuekart.bloodbank.dependencyInjection.DataBaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(@DataBaseInfo dbName: String, @ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java , dbName).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideAddressHelper(addressHelperImpl: AddressHelperImpl): AddressHelper {
        return addressHelperImpl
    }

    @Provides
    @Singleton
    internal fun provideBloodBankHelper(bloodBankHelperImpl: BloodBankHelperImpl): BloodBankHelper {
        return bloodBankHelperImpl
    }

    @Provides
    @DataBaseInfo
    internal fun provideDatabaseName(): String {
        return "bloodbank.db"
    }

    @Provides
    @Singleton
    internal fun provideAppDbHelper(appDataBase: AppDataBase): AppDbHelper {
        return AppDbHelper(appDataBase)
    }

}