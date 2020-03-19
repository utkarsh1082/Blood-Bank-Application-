package com.valuekart.bloodbank.ui.bloodBank.updateBloodBank

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.ui.base.BaseActivity
import javax.inject.Inject

class UpdateBloodBankActivity : BaseActivity<UpdateBloodBankViewModel>() {
    @Inject
    override lateinit var viewModel: UpdateBloodBankViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_blood_bank)
        val bloodBank = BloodBank()
        val address = Address()

        val id=findViewById<TextView>(R.id.patientId)
        val name = findViewById<EditText>(R.id.bloodBankName)

        val owner = findViewById<EditText>(R.id.owner)

        val capacity = findViewById<EditText>(R.id.capacity)

        val street = findViewById<EditText>(R.id.street_locality)

        val houseNo = findViewById<EditText>(R.id.houseNo)

        val updateButton = findViewById<Button>(R.id.updatePatient)

        val state = findViewById<EditText>(R.id.state)

        val city = findViewById<EditText>(R.id.city)

        val pinCode = findViewById<EditText>(R.id.pinCode)

        val contactNo = findViewById<EditText>(R.id.contactNo)

        updateButton.setOnClickListener {
            bloodBank.id=id.text.toString().toLong()
            bloodBank.nameOfBloodBank = name.text.toString()
            bloodBank.owner = owner.text.toString()
            if (capacity.text.toString().matches("^[0-9]+\$".toRegex()))
                bloodBank.capacity = Integer.parseInt(capacity.toString())
            else
                capacity.setError("Enter Correct Capacity")
            address.houseNo = houseNo.text.toString()
            address.street = street.text.toString()
            address.city = city.text.toString()
            address.state = state.text.toString()
            address.zipCode = pinCode.text.toString()
            address.contactNumber = contactNo.text.toString()
            bloodBank.address=address
            if (capacity.text.toString().matches("^[0-9]+\$".toRegex()))
                viewModel.updateBloodBank(bloodBank,address,this)
            else
                capacity.error = "Enter Correct Capacity"

        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, UpdateBloodBankActivity::class.java)
        }
    }
}