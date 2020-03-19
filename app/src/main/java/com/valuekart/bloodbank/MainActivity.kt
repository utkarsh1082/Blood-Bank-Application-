package com.valuekart.bloodbank

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.valuekart.bloodbank.ui.bloodBank.BloodBankActivity
import com.valuekart.bloodbank.ui.donateBlood.DonateBloodActivity
import com.valuekart.bloodbank.ui.donors.DonorsActivity
import com.valuekart.bloodbank.ui.getBlood.GetBloodActivity
import com.valuekart.bloodbank.ui.patients.PatientsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bloodBankActivityButton=findViewById<Button>(R.id.bloodBanks)
        val patientsActivityButton=findViewById<Button>(R.id.patients)
        val donorsActivityButton=findViewById<Button>(R.id.donors)
        val donateBloodActivityButton=findViewById<Button>(R.id.donateBlood)
        val getBloodActivityButton=findViewById<Button>(R.id.getBloodGroup)
        patientsActivityButton.setOnClickListener {
            val intent=PatientsActivity.newIntent(this)
            startActivity(intent)
        }
        donorsActivityButton.setOnClickListener {
            val intent=DonorsActivity.newIntent(this)
            startActivity(intent)
        }
        bloodBankActivityButton.setOnClickListener {
            val intent= BloodBankActivity.newIntent(this)
            startActivity(intent)
        }
        donateBloodActivityButton.setOnClickListener {
            val intent= DonateBloodActivity.newIntent(this)
            startActivity(intent)
        }
        getBloodActivityButton.setOnClickListener {
            val intent= GetBloodActivity.newIntent(this)
            startActivity(intent)
        }
    }
}
