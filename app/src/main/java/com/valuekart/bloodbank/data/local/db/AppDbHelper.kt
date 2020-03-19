package com.valuekart.bloodbank.data.local.db

import android.content.Context
import com.valuekart.bloodbank.data.local.db.address.AddressHelperImpl
import com.valuekart.bloodbank.data.local.db.bloodBank.BloodBankHelperImpl
import com.valuekart.bloodbank.data.local.db.donateBlood.DonateBloodHelperImpl
import com.valuekart.bloodbank.data.local.db.donor.DonorHelperImpl
import com.valuekart.bloodbank.data.local.db.getBlood.GetBloodHelperImpl
import com.valuekart.bloodbank.data.local.db.patient.PatientHelperImpl
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.data.model.entity.Donor
import com.valuekart.bloodbank.data.model.entity.Patient
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject constructor(private val mAppDatabase: AppDataBase) : DbHelper {
    private val bloodBankHelper = BloodBankHelperImpl(mAppDatabase)
    private val addressHelper = AddressHelperImpl(mAppDatabase)
    private val donorHelper = DonorHelperImpl(mAppDatabase)
    private val patientHelper = PatientHelperImpl(mAppDatabase)
    private val donateBloodHelper = DonateBloodHelperImpl(mAppDatabase)
    private val getBloodHelper = GetBloodHelperImpl(mAppDatabase)

    override fun donateBlood(donorId: Long, bloodBankId: Long, quantity: Long, context: Context) {
        return donateBloodHelper.donateBlood(donorId, bloodBankId, quantity, context)
    }

    override fun insertPatient(patient: Patient): Single<Long> {
        return patientHelper.insertPatient(patient)
    }

    override fun getPatient(patientId: Long): Single<Patient> {
        return patientHelper.getPatient(patientId)
    }

    override fun updatePatient(patient: Patient, address: Address, context: Context) {
        return patientHelper.updatePatient(patient, address, context)
    }

    override fun insertDonor(donor: Donor): Single<Long> {
        return donorHelper.insertDonor(donor)
    }

    override fun getDonor(donorId: Long): Single<Donor> {
        return donorHelper.getDonor(donorId)
    }

    override fun updateDonor(donor: Donor, address: Address, context: Context) {
        return donorHelper.updateDonor(donor, address, context)
    }

    override fun updateBloodBank(bloodBank: BloodBank, address: Address, context: Context) {
        return bloodBankHelper.updateBloodBank(bloodBank, address, context)
    }

    override fun getLastAdressId(): Single<Long> {
        return addressHelper.getLastAdressId()
    }

    override fun insertBloodBank(bloodBank: BloodBank): Single<Long> {
        return bloodBankHelper.insertBloodBank(bloodBank)
    }

    override fun insertAddress(address: Address) {
        return addressHelper.insertAddress(address)
    }

    override fun getBloodBank(bloodBankId: Long): Single<BloodBank> {
        return bloodBankHelper.getBloodBank(bloodBankId)
    }

    override fun getAddressById(addressId: Long): Single<Address> {
        return addressHelper.getAddressById(addressId)
    }
}