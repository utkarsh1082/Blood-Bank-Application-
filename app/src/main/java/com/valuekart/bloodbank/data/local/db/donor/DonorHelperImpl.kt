package com.valuekart.bloodbank.data.local.db.donor

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Donor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DonorHelperImpl(private val mAppDatabase: AppDataBase) : DonorHelper {

    override fun insertDonor(donor: Donor): Single<Long> {
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val x = it.donorDao().insert(donor)
                Log.d("Insert", x.toString())
            }, {
                it.printStackTrace()
            }))
        return mAppDatabase.donorDao().getLastId().observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    override fun getDonor(donorId: Long): Single<Donor> {
        return mAppDatabase.donorDao().getDonorById(donorId).map {
            Log.d("jjj", it.id.toString())
            attachAddress(it)
        }
    }

    private fun attachAddress(donor: Donor): Donor {
        val addressId = donor.fkAddressId
        if (addressId != null) {
            (mAppDatabase.addressDao().getAddressById(addressId).subscribe({
                Log.d("jjj1", it.id.toString())
                donor.address = it
            }, {
                it.printStackTrace()
            }))
        }
        return donor
    }

    override fun updateDonor(donor: Donor, address: Address, context: Context) {
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val appDataBase = it
                val donorId = donor.id
                Log.d("Update2", donorId.toString())
                if (donorId != null) {
                    getDonor(donorId)
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            val addressId = it.fkAddressId
                            Log.d("Update1", addressId.toString() + " " + it.id.toString())
                            if (addressId != null) {
                                Log.d(
                                    "Updateadd",
                                    addressId.toString() + " " + address.id.toString() + " " + address.city
                                )
                                address.id = addressId
                                val y = appDataBase.addressDao().update(address)
                                Log.d("Update3", y.toString())

                            }
                            donor.fkAddressId = addressId
                            appDataBase.donorDao().update(donor)
                        }, {
                            (context as Activity).runOnUiThread {
                                Toast.makeText(context, "DonorId not found", Toast.LENGTH_LONG).show()
                            }
                        }
                        )}
                }
            , {
                it.printStackTrace()
            }))
        Toast.makeText(context, "Donor info is updated", Toast.LENGTH_LONG).show()
    }
}
