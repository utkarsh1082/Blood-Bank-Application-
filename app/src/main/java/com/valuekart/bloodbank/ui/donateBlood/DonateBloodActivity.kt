package com.valuekart.bloodbank.ui.donateBlood

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.ui.base.BaseActivity
import javax.inject.Inject

class DonateBloodActivity : BaseActivity<DonateBloodViewModel>() {
    @Inject
    override lateinit var viewModel: DonateBloodViewModel
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donate_blood)
        val donorId = findViewById<EditText>(R.id.donorId)

        val bloodBankId = findViewById<EditText>(R.id.bloodBankId)

        val quantity = findViewById<EditText>(R.id.owner)
        val donate=findViewById<Button>(R.id.donateBloodButton)
        donate.setOnClickListener {
            viewModel.donateBlood(donorId.text.toString().toLong(),bloodBankId.text.toString().toLong(),quantity.text.toString().toLong(),this)
        }
    }
        companion object {
            fun newIntent(context: Context): Intent {
                return Intent(context, DonateBloodActivity::class.java)
            }
        }
    }