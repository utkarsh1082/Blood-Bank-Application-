package com.valuekart.bloodbank.data.model.entity

import android.arch.persistence.room.*

@Entity(
    tableName = "bloodBank", indices = [Index("fkAddressId")],
    foreignKeys = [
        ForeignKey(
            entity = Address::class,
            parentColumns = ["id"],
            childColumns = ["fkAddressId"]
        )]
)
data class BloodBank (

    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var nameOfBloodBank: String,

    @ColumnInfo(name = "fkAddressId")
    var fkAddressId: Long?,

    @Ignore
    var address: Address ,

    @ColumnInfo(name = "ownerName")
    var owner: String,

    @ColumnInfo(name = "capacity")
    var capacity: Int
    )
{
    constructor():this(null,"",null,Address(),"",0)
}