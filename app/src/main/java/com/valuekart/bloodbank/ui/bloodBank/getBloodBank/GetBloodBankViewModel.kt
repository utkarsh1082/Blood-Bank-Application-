package com.valuekart.bloodbank.ui.bloodBank.addBloodBank

import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.ui.base.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

class GetBloodBankViewModel
@Inject constructor(val appDbHelper: AppDbHelper) : BaseViewModel() {
    fun getBloodBank(bloodBankId: Long): Single<BloodBank> {
            return appDbHelper.getBloodBank(bloodBankId)
    }
}