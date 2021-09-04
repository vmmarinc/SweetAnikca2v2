package com.vmmarinc.sweetanikca2.ui.fragments

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.ui.adapters.CommentAdapter
import com.vmmarinc.sweetanikca2.ui.listeners.CommentListener
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.databinding.FragmentCommentBinding
import com.vmmarinc.sweetanikca2.ui.viewmodels.CommentViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.ext.getScopeId
import java.time.Instant


/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {
    // TODO: Rename and change types of

    private var _binding: FragmentCommentBinding? = null
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentManager: LinearLayoutManager
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private var _name : String = "UserByDefault"


    private val commentViewModel: CommentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()


        commentAdapter = CommentAdapter (
            listOf()

        )

        commentAdapter.listener = object: CommentListener {
            override fun onClick(comment: Comment){

                db.collection("comments")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            /*Log.d("Click", result.getScopeId())*/
                            /*Log.d(ContentValues.TAG, "${document.id} => ${document.data}")*/
                        }

                    }
                    /*.addOnFailureListener { exception ->
                        *//*Log.w(TAG, "Error getting documents.", exception)*//*
                    }*/

                _name = comment.name.toString()
                
                Log.d("Click", (db.collection("comments").whereEqualTo("name", _name).toString())).toString()
                Log.d("Click", comment.name!!)
                Log.d("Click", comment.description!!)
                findNavController().navigate(R.id.action_commentFragment2_to_editCommentFragment)
            }
        }
        commentManager = LinearLayoutManager(requireContext())
        binding.commentRecycler.apply{
            adapter = commentAdapter
            layoutManager = commentManager
        }

        commentViewModel.loadComments()
        commentViewModel.comment.observe(viewLifecycleOwner, Observer { comments ->
            commentAdapter.newDataset(comments)

        })

        binding.addCommentButton.setOnClickListener {
            findNavController().navigate(R.id.action_commentFragment2_to_addCommentFragment)
        }


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_comment, container, false)
    }


}