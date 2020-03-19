package com.valuekart.bloodbank.data.local.dao

import android.arch.persistence.room.*
import com.valuekart.bloodbank.data.model.entity.Patient
import io.reactivex.Single

@Dao
interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(patient: Patient): Long

    @Update
    fun update(patient: Patient): Int

    @Query("SELECT*FROM patient WHERE id= :patientId LIMIT 1")
    fun getPatientById(patientId:Long): Single<Patient>

    @Query("SELECT*FROM patient")
    fun getAll():List<Patient>

    @Query("SELECT id from patient order by id DESC limit 1" )
    fun getLastId(): Single<Long>

    @Query("SELECT bloodGroup from patient WHERE id= :patientId LIMIT 1 " )
    fun getBloodGroupByPatientId(patientId: Long): Single<String>
}