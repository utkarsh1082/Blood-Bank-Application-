package com.valuekart.bloodbank.data.local.db.bloodBank

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BloodBankHelperImpl
@Inject constructor(private val mAppDatabase: AppDataBase) : BloodBankHelper {
    override fun insertBloodBank(bloodBank: BloodBank): Single<Long> {
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("bloodbankid", bloodBank.fkAddressId.toString() + " " + bloodBank.nameOfBloodBank)
                val x = it.bloodBankDao().insert(bloodBank)
                Log.d("Insert", x.toString())
            }, {
                it.printStackTrace()
            }))
        return mAppDatabase.bloodBankDao().getLastId().observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    override fun getBloodBank(bloodBankId: Long): Single<BloodBank> {
        return mAppDatabase.bloodBankDao().getBloodBankById(bloodBankId).map {
            Log.d("jjj", it.id.toString() + " " + it.fkAddressId)
            attachAddress(it)
        }
    }

    private fun attachAddress(bloodBank: BloodBank): BloodBank {
        val addressId = bloodBank.fkAddressId
        Log.d("bloodbankid", bloodBank.fkAddressId.toString() + " " + addressId)
        if (addressId != null) {
            (mAppDatabase.addressDao().getAddressById(addressId).subscribe({
                Log.d("jjj1", it.id.toString())
                bloodBank.address = it
            }, {
                it.printStackTrace()
            }))
        }
        return bloodBank
    }

    override fun updateBloodBank(bloodBank: BloodBank, address: Address, context: Context) {
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val appDataBase = it
                val bloodBankId = bloodBank.id
                Log.d("Update2", bloodBankId.toString())
                if (bloodBankId != null) {
                    getBloodBank(bloodBankId)
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            val addressId = it.fkAddressId
                            Log.d("Update1", addressId.toString() + " " + it.id.toString() + " " + it.nameOfBloodBank)
                            if (addressId != null) {
                                address.id = addressId
                                appDataBase.addressDao().update(address)
                            }
                            bloodBank.fkAddressId = addressId
                            val x=appDataBase.bloodBankDao().update(bloodBank)
                        }, {
                            (context as Activity).runOnUiThread {
                                Toast.makeText(context, "BloodBankId not found", Toast.LENGTH_LONG).show()
                            }
                        }
                        )
                }

            }, {
                it.printStackTrace()
            }))
        Toast.makeText(context, "BloodBank info is updated", Toast.LENGTH_LONG).show()
    }
}