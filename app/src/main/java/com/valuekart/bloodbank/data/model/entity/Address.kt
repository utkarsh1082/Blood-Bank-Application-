package com.valuekart.bloodbank.data.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "address")
data class  Address (

    @PrimaryKey(autoGenerate = true)
    var id: Long? ,

    @ColumnInfo(name = "contact_number")
    var contactNumber: String?,

    @ColumnInfo(name = "address_1")
    var houseNo: String?,

    @ColumnInfo(name = "address_2")
    var street: String?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "zip_code")
    var zipCode: String?,

    @ColumnInfo(name = "state")
    var state: String?

)
{
    constructor():this(null,null,null,null,null,null,null)
}