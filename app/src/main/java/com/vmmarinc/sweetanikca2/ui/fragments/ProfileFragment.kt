package com.vmmarinc.sweetanikca2.ui.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.databinding.FragmentProfileBinding
import com.vmmarinc.sweetanikca2.ui.activities.LoginActivity
import com.vmmarinc.sweetanikca2.ui.activities.MainActivity
import com.vmmarinc.sweetanikca2.ui.viewmodels.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.jar.Manifest


class ProfileFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_IMAGE = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        checkPermission()

        loginViewModel.isLoggedIn()
        loginViewModel.user.observe(viewLifecycleOwner, Observer{user ->
            if(user!= null){
                binding.profileName.text = user.displayName
                if(user.photoUrl != null){
                    Glide.with(binding.root).load(user.photoUrl).into(binding.profileImage)
                    //db.collection("users").document("Uvc46DpX2f6cY8rvTvyW").set(hashMapOf("profileimage" to user.photoUrl.toString()))
                }
            }else{
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

        })

        binding.logoutButton.setOnClickListener {
            loginViewModel.logOut()
        }

        binding.profileImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                    intent.resolveActivity(requireActivity().packageManager)?.also {
                        startActivityForResult(intent, REQUEST_IMAGE)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_IMAGE) {
                val bitmap = data?.extras?.get("data") as Bitmap
                //binding.profileImage.setImageBitmap(bitmap)
                loginViewModel.uploadimage(bitmap)
            }
        }
    }

    private fun checkPermission(){
        if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        }
    }

}