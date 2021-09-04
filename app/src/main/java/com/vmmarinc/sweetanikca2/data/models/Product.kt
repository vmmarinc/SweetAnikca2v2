package com.vmmarinc.sweetanikca2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey var id : Int? = null,
    var image : String? = null,
    var title : String? = null,
    var price : String? = null,
    var description : String? = null

)
