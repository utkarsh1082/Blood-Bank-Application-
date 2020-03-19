package com.valuekart.bloodbank.data.local.db.getBlood

import com.valuekart.bloodbank.data.local.db.AppDataBase
import io.reactivex.Single
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription
import io.reactivex.schedulers.Schedulers

class GetBloodHelperImpl(private val mAppDatabase: AppDataBase):GetBloodHelper {}