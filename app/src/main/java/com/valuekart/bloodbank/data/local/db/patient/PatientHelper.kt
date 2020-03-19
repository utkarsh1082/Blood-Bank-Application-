package com.valuekart.bloodbank.data.local.db.patient

import android.content.Context
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Patient
import io.reactivex.Single

interface PatientHelper {
    fun insertPatient(patient: Patient): Single<Long>

    fun getPatient(patientId: Long): Single<Patient>

    fun updatePatient(patient: Patient, address: Address,context: Context)
}