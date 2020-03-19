package com.valuekart.bloodbank.data.local.dao

import android.arch.persistence.room.*
import com.valuekart.bloodbank.data.model.entity.BloodGroup
import io.reactivex.Single

@Dao
interface BloodGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bloodGroup: BloodGroup): Long

    @Update
    fun update(bloodGroup: BloodGroup): Int

    @Query("SELECT *FROM bloodGroup WHERE fkBloodBankId= :bloodBankId LIMIT 1")
    fun getBloodGroupById(bloodBankId: Long): Single<BloodGroup>

    @Query("SELECT *FROM bloodGroup")
    fun getAll(): Single<List<BloodGroup>>

    @Query("SELECT id from bloodGroup order by id DESC limit 1")
    fun getLastId(): Single<Long>

    @Query("SELECT fkBloodBankId  from bloodGroup where APositive>:quantity ")
    fun getBloodBanksWithAPositive(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where ANegative>:quantity ")
    fun getBloodBanksWithANegative(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where ABPositive>:quantity ")
    fun getBloodBanksWithABPositive(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where ABNegative>:quantity ")
    fun getBloodBanksWithABNegative(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where BPositive>:quantity ")
    fun getBloodBanksWithBPositive(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where BNegative>:quantity ")
    fun getBloodBanksWithBNegative(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where OPositive>:quantity ")
    fun getBloodBanksWithOPositive(quantity:Long): Single<List<Long>>

    @Query("SELECT fkBloodBankId from bloodGroup where ONegative>:quantity ")
    fun getBloodBanksWithONegative(quantity:Long): Single<List<Long>>
}