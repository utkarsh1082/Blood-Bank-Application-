package com.valuekart.bloodbank.ui.patients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.ui.base.BaseActivity
import com.valuekart.bloodbank.ui.patients.addPatient.AddPatientActivity
import com.valuekart.bloodbank.ui.patients.getPatient.GetPatientActivity
import com.valuekart.bloodbank.ui.patients.updatePatient.UpdatePatientActivity
import javax.inject.Inject

class PatientsActivity : BaseActivity<PatientsViewModel>() {
    @Inject
    override lateinit var viewModel: PatientsViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patients)
        val addPatientButton = findViewById<Button>(R.id.addPatient)
        val getPatientButton = findViewById<Button>(R.id.getPatientInfo)
        val updatePatientButton = findViewById<Button>(R.id.updatePatientInfo)
        addPatientButton.setOnClickListener {
            val intent = AddPatientActivity.newIntent(this)
            startActivity(intent)
        }
        getPatientButton.setOnClickListener {
            val intent = GetPatientActivity.newIntent(this)
            startActivity(intent)
        }
        updatePatientButton.setOnClickListener {
            val intent = UpdatePatientActivity.newIntent(this)
            startActivity(intent)
        }

    }
        companion object {
            fun newIntent(context: Context): Intent {
                return Intent(context, PatientsActivity::class.java)
            }
        }
    }