package com.valuekart.bloodbank.dependencyInjection.builder

import com.valuekart.bloodbank.ui.bloodBank.getBloodBank.GetBloodBankActivity
import com.valuekart.bloodbank.ui.bloodBank.BloodBankActivity
import com.valuekart.bloodbank.ui.bloodBank.addBloodBank.AddBloodBankActivity
import com.valuekart.bloodbank.ui.bloodBank.updateBloodBank.UpdateBloodBankActivity
import com.valuekart.bloodbank.ui.donateBlood.DonateBloodActivity
import com.valuekart.bloodbank.ui.donors.DonorsActivity
import com.valuekart.bloodbank.ui.donors.addDonor.AddDonorActivity
import com.valuekart.bloodbank.ui.donors.getDonor.GetDonorActivity
import com.valuekart.bloodbank.ui.donors.updateDonor.UpdateDonorActivity
import com.valuekart.bloodbank.ui.getBlood.GetBloodActivity
import com.valuekart.bloodbank.ui.patients.PatientsActivity
import com.valuekart.bloodbank.ui.patients.addPatient.AddPatientActivity
import com.valuekart.bloodbank.ui.patients.getPatient.GetPatientActivity
import com.valuekart.bloodbank.ui.patients.updatePatient.UpdatePatientActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    internal abstract fun bindAddBloodBank(): AddBloodBankActivity

    @ContributesAndroidInjector
    internal abstract fun bindBloodBank(): BloodBankActivity

    @ContributesAndroidInjector
    internal abstract fun bindPatients(): PatientsActivity

    @ContributesAndroidInjector
    internal abstract fun bindDonors(): DonorsActivity

    @ContributesAndroidInjector
    internal abstract fun bindGetBloodBank(): GetBloodBankActivity

    @ContributesAndroidInjector
    internal abstract fun bindDonorActivity():AddDonorActivity

    @ContributesAndroidInjector
    internal abstract fun bindGetDonorActivity(): GetDonorActivity

    @ContributesAndroidInjector
    internal abstract fun bindUpdateBloodBankActivity(): UpdateBloodBankActivity

    @ContributesAndroidInjector
    internal abstract fun bindUpdateDonorActivity(): UpdateDonorActivity

    @ContributesAndroidInjector
    internal abstract fun bindAddPatientActivity(): AddPatientActivity

    @ContributesAndroidInjector
    internal abstract fun bindGetPatientActivity(): GetPatientActivity

    @ContributesAndroidInjector
    internal abstract fun bindUpdatePatientActivity(): UpdatePatientActivity

    @ContributesAndroidInjector
    internal abstract fun bindDonateBloodActivity(): DonateBloodActivity

    @ContributesAndroidInjector
    internal abstract fun bindGetBloodActivity(): GetBloodActivity
}