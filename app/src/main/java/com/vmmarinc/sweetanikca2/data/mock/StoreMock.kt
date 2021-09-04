package com.vmmarinc.sweetanikca2.data.mock

import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.data.models.StoreInfo

class StoreMock {
    fun loadStoreInfo(): StoreInfo{
        return StoreInfo(
            1,
            "Sweet Anikca",
            "https://www.nicepng.com/png/full/255-2556817_elegant-images-of-birthday-cakes-png-cake-png.png",
            "Donde vivo 123",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididu",
            null,
            null
        )
    }
}