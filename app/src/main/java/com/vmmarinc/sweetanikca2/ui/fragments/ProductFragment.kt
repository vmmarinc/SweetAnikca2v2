package com.vmmarinc.sweetanikca2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.vmmarinc.sweetanikca2.R
import com.vmmarinc.sweetanikca2.ui.adapters.ProductAdapter
import com.vmmarinc.sweetanikca2.ui.listeners.ProductListener
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.databinding.FragmentProductBinding
import com.vmmarinc.sweetanikca2.ui.viewmodels.ProductViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productManager: GridLayoutManager
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by sharedViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        productAdapter = ProductAdapter(
            listOf()

        )

        productAdapter.listener = object: ProductListener {
            override fun onClick(product: Product){
                //Log.d("Click", product.title!!)
                productViewModel.selectProduct(product)
                findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
            }
        }
        productManager = GridLayoutManager(requireContext(), 2)
        binding.productRecycle.apply{
            adapter = productAdapter
            layoutManager = productManager
        }

        productViewModel.loadProducts()
        productViewModel.product.observe(viewLifecycleOwner, Observer { products ->
            productAdapter.newDataset(products)

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container,false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product, container, false)
    }



}