package com.valuekart.bloodbank.ui.patients.getPatient

import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Patient
import com.valuekart.bloodbank.ui.base.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

class GetPatientViewModel@Inject constructor(val appDbHelper: AppDbHelper): BaseViewModel() {
    fun getPatient(patientId:Long):Single<Patient>{
        return appDbHelper.getPatient(patientId)
    }
}