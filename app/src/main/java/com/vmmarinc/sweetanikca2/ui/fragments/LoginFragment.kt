package com.vmmarinc.sweetanikca2.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.databinding.FragmentLoginBinding
import com.vmmarinc.sweetanikca2.ui.activities.MainActivity
import com.vmmarinc.sweetanikca2.ui.viewmodels.LoginViewModel
import com.vmmarinc.sweetanikca2.utils.isValid
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        _binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.loginSignup.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if (user != null){
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
            requireActivity().finish()
        })

        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })
        binding.loginButton.setOnClickListener {
            var isValid = true
            if (!binding.loginEmail.text.isValid()){
                isValid = false
                binding.loginEmailLayout.error = "Correo invalido"
            } else{
                binding.loginEmailLayout.error = null
            }

            if (binding.loginPassword.text.isNullOrEmpty() || binding.loginPassword.text.toString().length < 4){
                isValid = false
                binding.loginPasswordLayot.error = "Contraseña incorrecta"
            } else{
                binding.loginPasswordLayot.error = null
            }

            if(isValid){
                loginViewModel.login(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())
            }

        }
    }


}