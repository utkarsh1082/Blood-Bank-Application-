package com.valuekart.bloodbank.data.local.db.donateBlood

import android.content.Context

interface DonateBloodHelper {
    fun donateBlood(donorId:Long,bloodBankId:Long,quantity:Long,context:Context)
}