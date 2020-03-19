package com.valuekart.bloodbank.data.local.db.bloodBank

import android.content.Context
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import io.reactivex.Single

interface BloodBankHelper {
    fun insertBloodBank(bloodBank: BloodBank):Single<Long>

    fun getBloodBank(bloodBankId: Long): Single<BloodBank>

    fun updateBloodBank(bloodBank: BloodBank,address: Address,context: Context)
}