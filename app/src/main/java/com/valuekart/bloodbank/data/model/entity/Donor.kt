package com.valuekart.bloodbank.data.model.entity

import android.arch.persistence.room.*

@Entity(
    tableName = "donor", indices = [Index("fkAddressId")],
    foreignKeys = [
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["fkAddressId"]
        )]
)
data class Donor(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "age")
    var age: Int?,

    @ColumnInfo(name = "medicalHistory")
    var medicalHistory: String?,

    @ColumnInfo(name = "quantity")
    var quantity: Long?,

    @ColumnInfo(name = "bloodGroup")
    var bloodGroup: String?,

    @ColumnInfo(name = "fkAddressId")
    var fkAddressId: Long?,

    @Ignore
    var address: Address

) {
    constructor() : this(null, null, null, null, null, null,null,Address())
}