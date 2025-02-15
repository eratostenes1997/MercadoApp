package com.paprika.searchapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.paprika.searchapp.R
import com.paprika.searchapp.databinding.FragmentProductsBinding
import com.paprika.searchapp.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {
    private lateinit var adapter: ProductAdapter
    private var _binding: FragmentProductsBinding?=null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.arrowBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_productsFragment_to_searchFragment)
        }

        adapter = ProductAdapter(emptyList()){product->
            val action = ProductsFragmentDirections.actionProductsFragmentToDetailsFragment(product)
            Navigation.findNavController(binding.root).navigate(action)
        }
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = adapter


        val product = arguments?.let{
            ProductsFragmentArgs.fromBundle(it).product
        }?:""

        viewModel.searchProducts(product)

        viewModel.products.observe(viewLifecycleOwner){products->
            adapter.updateProducts(products)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){isLoading->
            binding.loagingPv.visibility = if (isLoading) View.VISIBLE else View.GONE
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}