package com.valuekart.bloodbank.data.model.entity

import android.arch.persistence.room.*

@Entity(
    tableName = "bloodGroup", indices = [Index("fkBloodBankId")],
    foreignKeys = [
        ForeignKey(
            entity = BloodBank::class,
            parentColumns = ["id"],
            childColumns = ["fkBloodBankId"]
        )]
)
data class BloodGroup (

    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "fkBloodBankId")
    var fkBloodBankId: Long?,

    @Ignore
    var bloodBank: BloodBank ,

    @ColumnInfo(name = "APositive")
    var APositive: Long,

    @ColumnInfo(name = "ANegative")
    var ANegative: Long,

    @ColumnInfo(name = "BPositive")
    var BPositive: Long,

    @ColumnInfo(name = "BNegative")
    var BNegative: Long,

    @ColumnInfo(name = "ABPositive")
    var ABPositive: Long,

    @ColumnInfo(name = "ABNegative")
    var ABNegative: Long,

    @ColumnInfo(name = "OPositive")
    var OPositive: Long,

    @ColumnInfo(name = "ONegative")
    var ONegative: Long
)
{
    constructor():this(null,null, BloodBank(),0,0,0,0,0,0,0,0)
}