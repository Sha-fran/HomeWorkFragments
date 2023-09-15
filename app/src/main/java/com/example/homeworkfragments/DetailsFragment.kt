package com.example.homeworkfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworkfragments.databinding.DetailsFragmentLayoutBinding

class DetailsFragment:Fragment() {
    private lateinit var binding: DetailsFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}
