package com.valuekart.bloodbank.data.local.db.address

import com.valuekart.bloodbank.data.local.db.AppDataBase
import com.valuekart.bloodbank.data.model.entity.Address
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressHelperImpl
@Inject constructor(private val mAppDatabase: AppDataBase) : AddressHelper {

    override fun insertAddress(address: Address){
        (Observable.just(mAppDatabase)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                it.addressDao().insert(address)
            },{ it.printStackTrace()}))
    }

    override fun getAddressById(addressId: Long): Single<Address> {
        return mAppDatabase.addressDao().getAddressById(addressId)
    }

    override fun getLastAdressId():Single<Long>{
        return mAppDatabase.addressDao().getLastId().observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

}