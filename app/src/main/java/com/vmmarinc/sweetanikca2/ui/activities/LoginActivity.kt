package com.vmmarinc.sweetanikca2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}