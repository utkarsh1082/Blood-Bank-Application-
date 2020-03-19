package com.valuekart.bloodbank.ui.donors.updateDonor

import android.content.Context
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.data.model.entity.Donor
import com.valuekart.bloodbank.data.model.entity.Patient
import com.valuekart.bloodbank.ui.base.BaseViewModel
import javax.inject.Inject

class UpdateDonorViewModel@Inject constructor(val appDbHelper: AppDbHelper):BaseViewModel() {
    fun updateDonor(donor: Donor, address: Address,context: Context) {
        appDbHelper.updateDonor(donor,address,context)
    }
}