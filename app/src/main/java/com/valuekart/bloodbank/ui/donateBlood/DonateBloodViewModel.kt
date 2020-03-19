package com.valuekart.bloodbank.ui.donateBlood

import android.content.Context
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.ui.base.BaseViewModel
import javax.inject.Inject

class DonateBloodViewModel@Inject constructor(val appDbHelper: AppDbHelper):BaseViewModel() {

    fun donateBlood(donorId:Long,bloodBankId:Long,quantity:Long,context: Context){
        appDbHelper.donateBlood(donorId,bloodBankId,quantity,context)
    }
}