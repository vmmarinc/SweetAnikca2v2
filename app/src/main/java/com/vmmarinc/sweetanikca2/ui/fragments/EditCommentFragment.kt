package com.vmmarinc.sweetanikca2.ui.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.databinding.FragmentAddCommentBinding
import com.vmmarinc.sweetanikca2.databinding.FragmentCommentBinding
import com.vmmarinc.sweetanikca2.databinding.FragmentEditCommentBinding
import com.vmmarinc.sweetanikca2.ui.activities.LoginActivity
import com.vmmarinc.sweetanikca2.ui.adapters.CommentAdapter
import com.vmmarinc.sweetanikca2.ui.listeners.CommentListener
import com.vmmarinc.sweetanikca2.ui.viewmodels.CommentViewModel
import com.vmmarinc.sweetanikca2.ui.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_add_comment.*
import kotlinx.android.synthetic.main.fragment_edit_comment.*
import org.koin.android.viewmodel.ext.android.viewModel


class EditCommentFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentEditCommentBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()
    private val commentViewModel: CommentViewModel by viewModel()
    private lateinit var commentAdapter: CommentAdapter

    private var userurl : String = "https://www.pngkit.com/png/full/660-6600821_circundado-usuario-macho-tipo-3-de-la-piel.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit_comment, container, false)
        _binding = FragmentEditCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()


        db.collection("comments").document("WiLzbbBUwygnqU5sSk0d").get().addOnSuccessListener {

            edit_comment.setText(it.get("description") as String?)
        }

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

            binding.editCommentButton.setOnClickListener {
                db.collection("comments")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d("Click", document.id)
                            Log.d(TAG, "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        /*Log.w(TAG, "Error getting documents.", exception)*/
                    }

                db.collection("comments").document("WiLzbbBUwygnqU5sSk0d").set(hashMapOf("description" to edit_comment.text.toString(),
                    "name" to binding.profileName.text.toString(),
                    "image" to userurl))


                findNavController().navigate(R.id.action_editCommentFragment_to_commentFragment2)
            }

        })


    }


}