package com.paprika.searchapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.paprika.searchapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding?=null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botonBuscar.setOnClickListener {
            val producto = binding.etProducto.text.toString()
            if (producto.isNotEmpty()) {
                val action = SearchFragmentDirections.actionSearchFragmentToProductsFragment(producto)
                Navigation.findNavController(binding.root).navigate(action)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Ingrese un término de búsqueda",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}