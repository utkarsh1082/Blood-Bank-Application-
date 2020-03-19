package com.valuekart.bloodbank.ui.bloodBank

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.ui.base.BaseActivity
import com.valuekart.bloodbank.ui.bloodBank.addBloodBank.AddBloodBankActivity
import com.valuekart.bloodbank.ui.bloodBank.getBloodBank.GetBloodBankActivity
import com.valuekart.bloodbank.ui.bloodBank.updateBloodBank.UpdateBloodBankActivity
import javax.inject.Inject

class BloodBankActivity : BaseActivity<BloodBankViewModel>() {
    @Inject
    override lateinit var viewModel: BloodBankViewModel
        internal set
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blood_banks)
        val addBloodBankButton=findViewById<Button>(R.id.addPatient)
        val getBloodBankButton=findViewById<Button>(R.id.getPatientInfo)
        val updateBloodBankButton=findViewById<Button>(R.id.updatePatientInfo)
        addBloodBankButton.setOnClickListener {
            val intent= AddBloodBankActivity.newIntent(this)
            startActivity(intent)
        }
        getBloodBankButton.setOnClickListener {
            val intent= GetBloodBankActivity.newIntent(this)
            startActivity(intent)
        }
        updateBloodBankButton.setOnClickListener {
            val intent= UpdateBloodBankActivity.newIntent(this)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BloodBankActivity::class.java)
        }
    }
}