package com.vmmarinc.sweetanikca2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey var id : Int? = null,
    var image: String? = null,
    var name: String? = null,
    var description: String? = null
)