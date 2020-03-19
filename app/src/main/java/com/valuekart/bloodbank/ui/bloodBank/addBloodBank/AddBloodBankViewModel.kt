package com.valuekart.bloodbank.ui.bloodBank.addBloodBank

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import com.valuekart.bloodbank.data.local.db.AppDbHelper
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddBloodBankViewModel
@Inject constructor(val appDbHelper: AppDbHelper) : BaseViewModel() {
    fun insertBloodBank(context: Context, address: Address, bloodBank: BloodBank) {
        appDbHelper.insertAddress(address)
        compositeDisposable.add(
            appDbHelper.getLastAdressId()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("addressId", it.toString())
                    bloodBank.fkAddressId = it
                    Log.d("id",bloodBank.fkAddressId.toString()+" "+bloodBank.id.toString())
                    appDbHelper.insertBloodBank(bloodBank).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
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
        builder.setMessage("Your Blood Bank Id is " + bloodBankId.toString())
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