package com.valuekart.bloodbank.data.local.db.donateBlood

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.model.entity.BloodGroup
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DonateBloodHelperImpl(private val mAppDatabase: AppDataBase) : DonateBloodHelper {
    var token = 1
    override fun donateBlood(donorId: Long, bloodBankId: Long, quantity: Long, context: Context) {
        Log.d("jjjj", donorId.toString() + " " + bloodBankId.toString() + " " + quantity.toString())
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                mAppDatabase.donorDao().getBloodGroupFromId(donorId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        val bloodGroupString = it
                        mAppDatabase.run {
                            bloodGroupDao().getBloodGroupById(bloodBankId)
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io())
                                .subscribe({
                                    val bloodGroup = getBlood(bloodGroupString, it, quantity, bloodBankId,context)
                                    val x = mAppDatabase.bloodGroupDao().update(bloodGroup)
                                    Log.d("Update", x.toString())

                                }, {
                                    Log.d("Error", "blood group not found")
                                    mAppDatabase.bloodBankDao().getBloodBankById(bloodBankId)
                                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                                        .subscribe({
                                            if (it.capacity < quantity) {
                                                val handler = Handler(Looper.getMainLooper())
                                                handler.post {
                                                    Toast.makeText(
                                                        context,
                                                        "BloodBank has exceeded its capacity,only " + (it.capacity).toString() + " units remaining!",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            } else {
                                                val bloodGroup = BloodGroup()
                                                if (bloodGroupString == "A+") {
                                                    bloodGroup.APositive += quantity
                                                } else if (bloodGroupString == "A-") {
                                                    bloodGroup.ANegative += quantity
                                                } else if (bloodGroupString == "B+") {
                                                    bloodGroup.BPositive += quantity
                                                } else if (bloodGroupString == "B-") {
                                                    bloodGroup.BNegative += quantity
                                                } else if (bloodGroupString == "AB+") {
                                                    bloodGroup.ABPositive += quantity
                                                } else if (bloodGroupString == "AB-") {
                                                    bloodGroup.ABNegative += quantity
                                                } else if (bloodGroupString == "O+") {
                                                    bloodGroup.OPositive += quantity
                                                } else if (bloodGroupString == "O-") {
                                                    bloodGroup.ONegative += quantity
                                                }
                                                bloodGroup.fkBloodBankId = bloodBankId
                                                val x = mAppDatabase.bloodGroupDao().insert(bloodGroup)
                                                Log.d("Insert", x.toString())
                                                val builder = AlertDialog.Builder(context)
                                                builder.setMessage(
                                                    "Blood Donated with Token Number " +
                                                            token.toString()
                                                )
                                                builder.setCancelable(true)

                                                builder.setPositiveButton(
                                                    "Ok"
                                                ) { dialogInterface: DialogInterface, i: Int ->
                                                    dialogInterface.cancel()
                                                }
                                                val handler = Handler(Looper.getMainLooper())
                                                handler.post {
                                                    val alertDialog = builder.create()
                                                    alertDialog.show()
                                                    token++
                                                }
                                            }
                                        }, {
                                            Log.d("Error", "blood bank not found")
                                            val handler = Handler(Looper.getMainLooper())
                                            handler.post {
                                                Toast.makeText(context, "Blood Bank not found", Toast.LENGTH_LONG)
                                                    .show()
                                            }
                                        })
                                }
                                )
                            bloodGroupDao().getAll().subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                                .subscribe({
                                    it.forEach {
                                        Log.d(
                                            "Info",
                                            it.id.toString() + " " + it.fkBloodBankId + " " + it.ABNegative + " " + it.ABPositive + " " + it.ANegative + it.APositive + " " + it.BNegative
                                        )
                                    }
                                }, {})
                        }
                    }, {
                        Log.d("Error", "Donor not found")
                        val handler = Handler(Looper.getMainLooper())
                        handler.post {
                            Toast.makeText(context, "Donor not found! Please Register", Toast.LENGTH_LONG).show()
                        }
                    })

            }, {

            }))
    }

    private fun getBlood(
        blood: String,
        bloodGroup: BloodGroup,
        quantity: Long,
        bloodBankId: Long,
        context: Context
    ): BloodGroup {
        val currentCapacity = calculateCapacity(bloodGroup)
        val capacity = mAppDatabase.bloodBankDao().getBloodBankCapacity(bloodBankId)
        if (capacity - currentCapacity >= quantity) {
            if (blood == "A+") {
                bloodGroup.APositive += quantity
            } else if (blood == "A-") {
                bloodGroup.ANegative += quantity
            } else if (blood == "B+") {
                bloodGroup.BPositive += quantity
            } else if (blood == "B-") {
                bloodGroup.BNegative += quantity
            } else if (blood == "AB+") {
                bloodGroup.ABPositive += quantity
            } else if (blood == "AB-") {
                bloodGroup.ABNegative += quantity
            } else if (blood == "O+") {
                bloodGroup.OPositive += quantity
            } else if (blood == "O-") {
                bloodGroup.ONegative += quantity
            }
                val builder = AlertDialog.Builder(context)
                builder.setMessage(
                    "Blood Donatedhow to"
                )
                builder.setCancelable(true)

                builder.setPositiveButton(
                    "Ok"
                ) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.cancel()
                }
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    val alertDialog = builder.create()
                    alertDialog.show()
                    token++
                }
            } else {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    Toast.makeText(
                        context,
                        "BloodBank has exceeded its capacity,only "+(capacity-currentCapacity).toString() + " units remaining!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        Log.d(
            "texting",
            currentCapacity.toString() + " " + capacity.toString() + " " + blood + " " + quantity.toString()
        )
        return (bloodGroup)
    }

    private fun calculateCapacity(bloodGroup: BloodGroup): Long {
        return bloodGroup.APositive + bloodGroup.ANegative + bloodGroup.BPositive + bloodGroup.BNegative + bloodGroup.ABPositive + bloodGroup.ABNegative + bloodGroup.OPositive + bloodGroup.ONegative
    }
}