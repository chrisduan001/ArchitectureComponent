package com.example.chris.architecturecomponent.model

import android.arch.persistence.room.*
import java.util.*

/**
 * Created by Chris on 1/8/18.
 */

@Entity
@TypeConverters(DateConverter::class)
data class BorrowModel(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        var itemName: String?,
        var personName: String?,
        var borrowDate: Date?,
        @Embedded
        var address: Address?
)

data class Address(
        var street: String?,
        var city: String?
)