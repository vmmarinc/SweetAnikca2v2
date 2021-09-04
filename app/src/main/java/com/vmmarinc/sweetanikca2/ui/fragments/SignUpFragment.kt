package com.vmmarinc.sweetanikca2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.databinding.FragmentLoginBinding
import com.vmmarinc.sweetanikca2.databinding.FragmentSignUpBinding
import com.vmmarinc.sweetanikca2.ui.viewmodels.LoginViewModel
import com.vmmarinc.sweetanikca2.utils.isValid
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sign_up, container, false)
        _binding= FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        })

        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })

        binding.signupButton.setOnClickListener {
            var isValid = true
            if(!binding.signupEmail.text.isValid()){
                isValid = false
                binding.signupEmailLayout.error = "Correo electrónico invalido"
            } else{
                binding.signupEmailLayout.error = null
            }

            if(binding.signupName.text.isNullOrEmpty()){
                isValid = false
                binding.signupNameLayout.error = "El nombre no puede estar vacio"
            } else{
                binding.signupNameLayout.error = null
            }

            if(binding.signupPassword.text.isNullOrEmpty() || binding.signupPassword.text.toString().length < 4){
                isValid = false
                binding.signupPasswordLayout.error = "Contraseña invalida"
            } else{
                binding.signupPasswordLayout.error = null
            }

            if (isValid){
                loginViewModel.signUp(binding.signupEmail.text.toString(), binding.signupName.text.toString(), binding.signupPassword.text.toString())

            }

            //db.collection("users").document().set(hashMapOf("name" to binding.signupName.toString()))
        }
    }
}