package com.vmmarinc.sweetanikca2.ui.listeners

import com.vmmarinc.sweetanikca2.data.models.Product

interface ProductListener {
    fun onClick(product: Product)
}