package com.valuekart.bloodbank.ui.patients.updatePatient

import android.content.Context
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Donor
import com.valuekart.bloodbank.data.model.entity.Patient
import com.valuekart.bloodbank.ui.base.BaseViewModel
import javax.inject.Inject

class UpdatePatientViewModel@Inject constructor(val appDbHelper: AppDbHelper): BaseViewModel() {
    fun updatePatient(patient: Patient, address: Address, context: Context) {
        appDbHelper.updatePatient(patient,address,context)
    }
}