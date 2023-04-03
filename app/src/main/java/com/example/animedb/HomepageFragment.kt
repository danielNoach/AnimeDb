package com.example.animedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.animedb.databinding.HomepageLayoutBinding

class HomepageFragment : Fragment() {

    private var x = 1
    private var _binding : HomepageLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomepageLayoutBinding.inflate(inflater, container,false)

        binding.AddFlpatingBtn.setOnClickListener{
            findNavController().navigate(R.id.action_homepageFragment_to_addanimeFragment)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}