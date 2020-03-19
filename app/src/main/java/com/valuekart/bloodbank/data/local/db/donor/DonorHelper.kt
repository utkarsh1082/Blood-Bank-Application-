package com.valuekart.bloodbank.data.local.db.donor

import android.content.Context
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Donor
import io.reactivex.Single

interface DonorHelper {
    fun insertDonor(donor: Donor): Single<Long>

    fun getDonor(donorId: Long): Single<Donor>

    fun updateDonor(donor: Donor, address: Address,context: Context)
}