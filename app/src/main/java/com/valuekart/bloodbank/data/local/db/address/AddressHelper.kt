package com.valuekart.bloodbank.data.local.db.address

import com.valuekart.bloodbank.data.model.entity.Address
import io.reactivex.Single

interface AddressHelper {
    fun insertAddress(address: Address)

    fun getAddressById(addressId: Long): Single<Address>

    fun getLastAdressId():Single<Long>

}