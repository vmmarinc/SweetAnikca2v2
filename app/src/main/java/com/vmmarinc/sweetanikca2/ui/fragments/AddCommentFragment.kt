package com.vmmarinc.sweetanikca2.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.databinding.FragmentAddCommentBinding
import com.vmmarinc.sweetanikca2.databinding.FragmentProductDetailBinding
import com.vmmarinc.sweetanikca2.ui.activities.LoginActivity
import com.vmmarinc.sweetanikca2.ui.viewmodels.CommentViewModel
import com.vmmarinc.sweetanikca2.ui.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_add_comment.*
import org.koin.android.viewmodel.ext.android.viewModel


class AddCommentFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    private var _binding: FragmentAddCommentBinding? = null

    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()
    //private val CommnetViewModel: CommentViewModel by viewModel()
    private var userurl : String = "https://www.pngkit.com/png/full/660-6600821_circundado-usuario-macho-tipo-3-de-la-piel.png"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_comment, container, false)
        _binding = FragmentAddCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        loginViewModel.isLoggedIn()
        loginViewModel.user.observe(viewLifecycleOwner, Observer{user ->
            if(user!= null){
                binding.profileName.text = user.displayName
                if(user.photoUrl != null){
                    userurl = user.photoUrl.toString()
                    Glide.with(binding.root).load(user.photoUrl).into(binding.profileImage)
                }
            }else{
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            binding.addCommentButton.setOnClickListener {
                db.collection("comments").add(hashMapOf("description" to add_comment.text.toString(),
                    "name" to binding.profileName.text.toString(),
                    "image" to userurl))
                findNavController().navigate(R.id.action_addCommentFragment_to_commentFragment2)
            }

        })


    }


}//"https://www.pngkit.com/png/full/660-6600821_circundado-usuario-macho-tipo-3-de-la-piel.png"
//Glide.with(binding.root).load(info.image).into(binding.homeImage)
//Glide.with(binding.root).load(user?.photoUrl).into(binding.profileImage)


