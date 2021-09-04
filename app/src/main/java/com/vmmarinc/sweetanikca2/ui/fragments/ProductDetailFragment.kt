package com.vmmarinc.sweetanikca2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.databinding.FragmentProductDetailBinding
import com.vmmarinc.sweetanikca2.ui.viewmodels.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.ext.getScopeName


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null

    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product_detail, container, false)
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        productViewModel.detail.observe(viewLifecycleOwner, Observer { detail ->
            binding.productDetailName.text = detail.title
            binding.productDetailDescription.text = detail.description
            Glide.with(binding.root).load(detail.image).into(binding.productDetailImage)
        })
    }


}