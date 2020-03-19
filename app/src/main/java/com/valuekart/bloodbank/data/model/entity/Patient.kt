package com.valuekart.bloodbank.data.model.entity

import android.arch.persistence.room.*

@Entity(
    tableName = "patient", indices = [Index("fKbloodBankId"), Index("fKAddressId")],
    foreignKeys = [
        ForeignKey(
            entity = BloodBank::class,
            parentColumns = ["id"],
            childColumns = ["fKbloodBankId"]
        ),
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["fKbloodBankId"]
        )
    ]
)
data class Patient(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "age")
    var age: Int?,

    @ColumnInfo(name = "disease")
    var disease: String?,

    @ColumnInfo(name = "bloodGroup")
    var bloodGroup: String?,

    @ColumnInfo(name = "fKbloodBankId")
    var fKbloodBankId: Long?,

    @Ignore
    var bloodBank: BloodBank,

    @ColumnInfo(name = "fKAddressId")
    var fKAddressId: Long?,

    @Ignore
    var address: Address
)
{
    constructor():this(null, null, null, null, null, null, BloodBank(),null,Address())
}