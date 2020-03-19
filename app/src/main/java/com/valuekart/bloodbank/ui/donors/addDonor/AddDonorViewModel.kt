package com.valuekart.bloodbank.ui.donors.addDonor

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Donor
import com.valuekart.bloodbank.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddDonorViewModel@Inject constructor(val appDbHelper: AppDbHelper): BaseViewModel() {
    fun insertDonor(context: Context, address: Address, donor: Donor) {
        appDbHelper.insertAddress(address)
        compositeDisposable.add(
            appDbHelper.getLastAdressId()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("addressId", it.toString())
                    donor.fkAddressId = it
                    appDbHelper.insertDonor(donor).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
        builder.setMessage("Your Donor Id is " + bloodBankId.toString())
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