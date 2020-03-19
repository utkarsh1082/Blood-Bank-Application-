package com.valuekart.bloodbank.ui.donors.getDonor

import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.data.model.entity.Donor
import com.valuekart.bloodbank.ui.base.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

class GetDonorViewModel@Inject constructor(val appDbHelper: AppDbHelper):BaseViewModel() {
    fun getDonor(donorId: Long): Single<Donor> {
        return appDbHelper.getDonor(donorId)
    }
}