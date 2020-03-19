package com.valuekart.bloodbank.ui.patients.addPatient

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Patient
import com.valuekart.bloodbank.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPatientViewModel@Inject constructor(val appDbHelper: AppDbHelper): BaseViewModel() {
    fun insertPatient(context: Context, address: Address, patient: Patient) {
        appDbHelper.insertAddress(address)
        compositeDisposable.add(
            appDbHelper.getLastAdressId()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("addressId", it.toString())
                    patient.fKAddressId = it
                    appDbHelper.insertPatient(patient).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showDialogBox(context, it)
                        }, {
                            it.printStackTrace()
                        }
                        )
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun showDialogBox(context: Context, bloodBankId: Long) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Your Patient Id is " + bloodBankId.toString())
        builder.setCancelable(true)

        builder.setPositiveButton(
            "Ok",
            { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            })
        val alertDialog = builder.create()
        alertDialog.show()
    }
}