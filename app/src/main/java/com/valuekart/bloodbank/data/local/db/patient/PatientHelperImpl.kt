package com.valuekart.bloodbank.data.local.db.patient

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Patient
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.app.Activity

class PatientHelperImpl(private val mAppDatabase: AppDataBase) : PatientHelper {

    override fun insertPatient(patient: Patient): Single<Long> {
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val x = it.patientDao().insert(patient)
                Log.d("Insert", x.toString())
            }, {
                it.printStackTrace()
            }))
        return mAppDatabase.patientDao().getLastId().observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    override fun getPatient(patientId: Long): Single<Patient> {
        return mAppDatabase.patientDao().getPatientById(patientId).map {
            attachAddress(it)
        }
    }

    private fun attachAddress(patient: Patient): Patient {
        val addressId = patient.fKAddressId
        if (addressId != null) {
            (mAppDatabase.addressDao().getAddressById(addressId).subscribe({
                patient.address = it
            }, {
                it.printStackTrace()
            }))
        }
        return patient
    }

    override fun updatePatient(patient: Patient, address: Address, context: Context) {
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val appDataBase = it
                val patientId = patient.id
                Log.d("Update2", patientId.toString())
                if (patientId != null) {
                    getPatient(patientId)
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            val addressId = it.fKAddressId
                            Log.d("Update1", addressId.toString() + " " + it.id.toString())
                            if (addressId != null) {
                                Log.d(
                                    "Updateadd",
                                    addressId.toString() + " " + address.id.toString() + " " + address.city
                                )
                                address.id = addressId
                                val y = appDataBase.addressDao().update(address)
                                Log.d("Update3", y.toString())
                            }
                            patient.fKAddressId = addressId
                            val x = appDataBase.patientDao().update(patient)
                            Log.d("Update", x.toString() + " " + patientId.toString())
                        }, {
                            (context as Activity).runOnUiThread {
                                Toast.makeText(context, "PatientId not found", Toast.LENGTH_LONG).show()
                            }
                        }
                        )}
            }, {
                it.printStackTrace()
            }))
        Toast.makeText(context, "Patient info is updated", Toast.LENGTH_LONG).show()
    }
}