package com.valuekart.bloodbank.ui.getBlood

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.valuekart.bloodbank.BloodBankApp
import com.valuekart.bloodbank.R
import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.model.entity.BloodBank
import com.valuekart.bloodbank.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetBloodActivity : BaseActivity<GetBloodViewModel>(), AdapterView.OnItemClickListener {

    @Inject
    override lateinit var viewModel: GetBloodViewModel
        internal set

    @Inject
    lateinit var mAppDataBase: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_blood)
        val patientEditText = findViewById<EditText>(R.id.patientId)
        val quantityEditText = findViewById<EditText>(R.id.quantity)
        val listView = findViewById<ListView>(R.id.listView)
        val showBloodBankButton = findViewById<Button>(R.id.showBloodBank)
        var arrayAdapter: ArrayAdapter<BloodBank>? = null
        showBloodBankButton.setOnClickListener {
            val patientId = patientEditText.text.toString()
            val quantity = quantityEditText.text.toString()
            mAppDataBase.patientDao().getBloodGroupByPatientId(patientId.toLong()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("List", it)
                    if (it == "A+") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithAPositive(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                bloodBanks.map {
                                    Log.d("jhsf",it.id.toString())
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else if (it == "A-") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithANegative(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else if (it == "B+") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithBPositive(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else if (it == "B-") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithBNegative(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else if (it == "AB+") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithABPositive(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                bloodBanks.map {
                                    Log.d("jhsf",it.id.toString())
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else if (it == "AB-") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithABNegative(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }

                                bloodBanks.map {
                                    Log.d("jhsf",it.id.toString())
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else if (it == "O+") {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithOPositive(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    } else {
                        mAppDataBase.bloodGroupDao().getBloodBanksWithONegative(quantity.toLong())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io()).subscribe({
                                val bloodBanks = ArrayList<BloodBank>()
                                it.map {
                                    mAppDataBase.bloodBankDao().getBloodBankById(it).subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io()).subscribe({
                                            bloodBanks.add(it)
                                        }, {})
                                }
                                bloodBanks.map {
                                    Log.d("jhsf",it.id.toString())
                                }
                                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bloodBanks)
                            }, { })
                    }
                    listView.adapter = arrayAdapter
                }, {

                })
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(this, "kjaefn", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GetBloodActivity::class.java)
        }
    }
}