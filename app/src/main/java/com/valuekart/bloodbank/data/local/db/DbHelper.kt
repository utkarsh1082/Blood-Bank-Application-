package com.valuekart.bloodbank.data.local.db

import com.valuekart.bloodbank.data.local.db.address.AddressHelper
import com.valuekart.bloodbank.data.local.db.bloodBank.BloodBankHelper
import com.valuekart.bloodbank.data.local.db.donateBlood.DonateBloodHelper
import com.valuekart.bloodbank.data.local.db.donor.DonorHelper
import com.valuekart.bloodbank.data.local.db.getBlood.GetBloodHelper
import com.valuekart.bloodbank.data.local.db.patient.PatientHelper

interface DbHelper : AddressHelper, PatientHelper, DonorHelper, BloodBankHelper, DonateBloodHelper, GetBloodHelper {
}