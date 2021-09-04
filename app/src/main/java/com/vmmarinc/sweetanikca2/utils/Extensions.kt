package com.vmmarinc.sweetanikca2.utils

import android.util.Patterns

fun CharSequence?.isValid() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()