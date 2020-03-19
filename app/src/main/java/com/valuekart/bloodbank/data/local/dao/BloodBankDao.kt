package com.valuekart.bloodbank.data.local.dao

import android.arch.persistence.room.*
import com.valuekart.bloodbank.data.model.entity.BloodBank
import io.reactivex.Single

@Dao
interface BloodBankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bloodBank: BloodBank): Long

    @Update
    fun update(bloodBank: BloodBank): Int

    @Query("SELECT*FROM bloodBank WHERE id= :bloodBankId LIMIT 1")
    fun getBloodBankById(bloodBankId:Long):Single<BloodBank>

    @Query("SELECT*FROM bloodBank")
    fun getAll():List<BloodBank>

    @Query("SELECT id from bloodBank order by id DESC limit 1" )
    fun getLastId():Single<Long>

    @Query("SELECT capacity from bloodBank WHERE id= :bloodBankId LIMIT 1" )
    fun getBloodBankCapacity(bloodBankId: Long):Int
}