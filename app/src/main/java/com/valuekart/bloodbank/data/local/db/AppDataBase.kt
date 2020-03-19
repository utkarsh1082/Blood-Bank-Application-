package com.valuekart.bloodbank.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.valuekart.bloodbank.data.local.dao.*
import com.valuekart.bloodbank.data.model.entity.*

@Database(
    entities = [
        Address::class,
        BloodBank::class,
        BloodGroup::class,
        Donor::class,
        Patient::class
    ], version = 15
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
    abstract fun bloodBankDao(): BloodBankDao
    abstract fun bloodGroupDao():BloodGroupDao
    abstract fun donorDao(): DonorDao
    abstract fun patientDao(): PatientDao
}