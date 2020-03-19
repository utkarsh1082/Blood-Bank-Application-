package com.valuekart.bloodbank.ui.patients.addPatient

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.data.model.entity.Address
import com.valuekart.bloodbank.data.model.entity.Patient
import com.valuekart.bloodbank.ui.base.BaseActivity
import javax.inject.Inject

class AddPatientActivity:BaseActivity<AddPatientViewModel>(),AdapterView.OnItemSelectedListener {
    var bloodGroup:String?=null
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        bloodGroup=p0?.getItemAtPosition(p2).toString()

    }
    @Inject
    override lateinit var viewModel: AddPatientViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_patient)
        val patient = Patient()
        val address = Address()

        val name = findViewById<TextView>(R.id.patientName)

        val age = findViewById<EditText>(R.id.owner)

        val illness = findViewById<EditText>(R.id.illness_if_any)

        val street = findViewById<EditText>(R.id.street_locality)

        val houseNo = findViewById<EditText>(R.id.houseNo)

        val submitButton = findViewById<Button>(R.id.submitButton)

        val state = findViewById<EditText>(R.id.state)

        val city = findViewById<EditText>(R.id.city)

        val pinCode = findViewById<EditText>(R.id.pinCode)

        val contactNo = findViewById<EditText>(R.id.contactNo)

        val spinner=findViewById<Spinner>(R.id.patient_spinner)

        val adapter= ArrayAdapter.createFromResource(this, R.array.bloodGroups,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
        spinner.onItemSelectedListener=this

        submitButton.setOnClickListener {

            patient.name = name.text.toString()
            if (age.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))
                patient.age = Integer.parseInt(age.text.toString())
            else {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Enter Correct Age")
                builder.setCancelable(true)

                builder.setPositiveButton(
                    "Ok",
                    { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    })
                val alertDialog = builder.create()
                alertDialog.show()
            }
            if(bloodGroup!=null) {
                patient.bloodGroup = bloodGroup
            }
            else{
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Enter a Blood Group")
                builder.setCancelable(true)

                builder.setPositiveButton(
                    "Ok",
                    { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    })
                val alertDialog = builder.create()
                alertDialog.show()
            }
            patient.disease = illness.text.toString()

            address.houseNo = houseNo.text.toString()
            address.street = street.text.toString()
            address.city = city.text.toString()
            address.state = state.text.toString()
            address.zipCode = pinCode.text.toString()
            address.contactNumber = contactNo.text.toString()

            if (age.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))
                viewModel.insertPatient(this, address, patient)

        }
    }
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddPatientActivity::class.java)
        }
    }
}