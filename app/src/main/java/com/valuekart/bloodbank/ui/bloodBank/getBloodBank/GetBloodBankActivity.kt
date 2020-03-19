package com.valuekart.bloodbank.ui.bloodBank.getBloodBank

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.ui.base.BaseActivity
import com.valuekart.bloodbank.ui.bloodBank.addBloodBank.GetBloodBankViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetBloodBankActivity : BaseActivity<GetBloodBankViewModel>() {

    @Inject
    override lateinit var viewModel: GetBloodBankViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_blood_bank)

        val name = findViewById<TextView>(R.id.bloodBankName)

        val owner = findViewById<TextView>(R.id.owner)

        val capacity = findViewById<TextView>(R.id.capacity)

        val street = findViewById<TextView>(R.id.street_locality)

        val houseNo = findViewById<TextView>(R.id.houseNo)

        val state = findViewById<TextView>(R.id.state)

        val city = findViewById<TextView>(R.id.city)

        val pinCode = findViewById<TextView>(R.id.pinCode)

        val contactNo = findViewById<TextView>(R.id.contactNo)

        val getId = findViewById<TextView>(R.id.patientId)

        val getDetailsButton = findViewById<Button>(R.id.getDetails)

        getDetailsButton.setOnClickListener {

            val bloodBankId = getId.text.toString().toLong()
            compositeDisposable.add(
                viewModel.getBloodBank(bloodBankId)
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Log.d("BloodBank1234", it.id.toString())
                        name.text = it.nameOfBloodBank
                        owner.text = it.owner
                        capacity.text = it.capacity.toString()
                        street.text = it.address.street
                        state.text = it.address.state
                        city.text = it.address.city
                        pinCode.text = it.address.zipCode
                        contactNo.text = it.address.contactNumber
                        houseNo.text = it.address.houseNo
                    }, {
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage("No Blood Bank Found With this ID")
                        builder.setCancelable(true)

                        builder.setPositiveButton(
                            "Ok"
                        ) { dialogInterface: DialogInterface, i: Int ->
                            dialogInterface.cancel()
                        }
                        runOnUiThread {
                            val alertDialog = builder.create()
                            alertDialog.show()
                        }
                        it.printStackTrace()
                    })
            )
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GetBloodBankActivity::class.java)
        }
    }
}