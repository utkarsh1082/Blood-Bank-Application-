package com.valuekart.bloodbank.ui.donors

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.ui.base.BaseActivity
import com.valuekart.bloodbank.ui.donors.addDonor.AddDonorActivity
import com.valuekart.bloodbank.ui.donors.getDonor.GetDonorActivity
import com.valuekart.bloodbank.ui.donors.updateDonor.UpdateDonorActivity
import javax.inject.Inject

class DonorsActivity : BaseActivity<DonorsViewModel>() {
    @Inject
    override lateinit var viewModel: DonorsViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donors)
        val addDonorButton=findViewById<Button>(R.id.addPatient)
        val getDonorButton=findViewById<Button>(R.id.getPatientInfo)
        val updateDonorButton=findViewById<Button>(R.id.updatePatientInfo)
        addDonorButton.setOnClickListener {
            val intent= AddDonorActivity.newIntent(this)
            startActivity(intent)
        }
        getDonorButton.setOnClickListener {
            val intent= GetDonorActivity.newIntent(this)
            startActivity(intent)
        }
        updateDonorButton.setOnClickListener {
            val intent= UpdateDonorActivity.newIntent(this)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DonorsActivity::class.java)
        }
    }
}