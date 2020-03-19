package com.valuekart.bloodbank.ui.donors.addDonor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Donor
import com.valuekart.bloodbank.ui.base.BaseActivity
import javax.inject.Inject

class AddDonorActivity : BaseActivity<AddDonorViewModel>(),AdapterView.OnItemSelectedListener {
    var bloodGroup:String?=null
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        bloodGroup=p0?.getItemAtPosition(p2).toString()

    }


    @Inject
    override lateinit var viewModel: AddDonorViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_donor)
        val donor = Donor()
        val address = Address()

        val name = findViewById<EditText>(R.id.nameOfDonor)

        val age = findViewById<EditText>(R.id.owner)

        val medicalHistory = findViewById<EditText>(R.id.medicalHistory)

        val street = findViewById<EditText>(R.id.street_locality)

        val houseNo = findViewById<EditText>(R.id.houseNo)

        val submitButton = findViewById<Button>(R.id.submitButton)

        val state = findViewById<EditText>(R.id.state)

        val city = findViewById<EditText>(R.id.city)

        val pinCode = findViewById<EditText>(R.id.pinCode)

        val contactNo = findViewById<EditText>(R.id.contactNo)


        val spinner=findViewById<Spinner>(R.id.spinner)

        val adapter= ArrayAdapter.createFromResource(this,R.array.bloodGroups,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
        spinner.onItemSelectedListener=this
        submitButton.setOnClickListener {

            donor.name = name.text.toString()
            if (age.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))
                donor.age = Integer.parseInt(age.text.toString())
            else {
                age.setError("Enter Correct Age")
            }
            if(bloodGroup!=null) {
                donor.bloodGroup = bloodGroup
            }

            donor.medicalHistory = medicalHistory.text.toString()

            address.houseNo = houseNo.text.toString()
            address.street = street.text.toString()
            address.city = city.text.toString()
            address.state = state.text.toString()
            address.zipCode = pinCode.text.toString()
            address.contactNumber = contactNo.text.toString()

            if (age.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))
                viewModel.insertDonor(this, address, donor)

        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddDonorActivity::class.java)
        }
    }
}