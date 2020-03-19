package com.valuekart.bloodbank.data.local.dao

import android.arch.persistence.room.*
import com.valuekart.bloodbank.data.model.entity.Donor
import io.reactivex.Single

@Dao
interface DonorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(donor: Donor): Long

    @Update
    fun update(donor: Donor): Int

    @Query("SELECT*FROM donor WHERE id = :donorId LIMIT 1")
    fun getDonorById(donorId:Long): Single<Donor>

    @Query("SELECT*FROM donor")
    fun getAll():List<Donor>

    @Query("SELECT id from donor order by id DESC limit 1" )
    fun getLastId(): Single<Long>

    @Query("SELECT bloodGroup from donor WHERE id = :donorId LIMIT 1" )
    fun getBloodGroupFromId(donorId: Long): Single<String>
}