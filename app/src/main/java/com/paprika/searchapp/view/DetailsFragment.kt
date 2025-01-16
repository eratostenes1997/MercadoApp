package com.paprika.searchapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.paprika.searchapp.R
import com.paprika.searchapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val product = arguments?.let {
            DetailsFragmentArgs.fromBundle(it).producto
        }

        product?.let {
            binding.tvDetailName.text = it.title
            binding.tvEstado.text = it.condition
            binding.tvPrice.text = it.price.toString()


            Glide.with(this)
                .load(it.thumbnail)
                .into(binding.ivDetailImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}