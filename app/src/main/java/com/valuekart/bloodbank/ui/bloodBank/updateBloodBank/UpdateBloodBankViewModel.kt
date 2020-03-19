package com.valuekart.bloodbank.ui.bloodBank.updateBloodBank

import android.content.Context
import android.util.Log
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.ui.base.BaseViewModel
import javax.inject.Inject

class UpdateBloodBankViewModel
@Inject constructor(val appDbHelper: AppDbHelper) : BaseViewModel() {
    fun updateBloodBank(bloodBank:BloodBank,address: Address,context: Context) {
       appDbHelper.updateBloodBank(bloodBank,address,context)
    }
}