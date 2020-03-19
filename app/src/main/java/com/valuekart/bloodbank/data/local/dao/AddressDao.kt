package com.valuekart.bloodbank.data.local.dao

import android.arch.persistence.room.*
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import io.reactivex.Single

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(address: Address): Long

    @Update
    fun update(address: Address): Int

    @Query("SELECT*FROM address WHERE id=:addressId LIMIT 1 ")
    fun getAddressById(addressId:Long): Single<Address>

    @Query("SELECT*FROM address")
    fun getAll():List<Address>

    @Query("SELECT id from address order by id DESC limit 1" )
    fun getLastId():Single<Long>

}