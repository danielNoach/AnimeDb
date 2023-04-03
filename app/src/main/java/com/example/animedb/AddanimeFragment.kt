package com.example.animedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.animedb.databinding.AddAnimeLayoutBinding
import com.example.animedb.databinding.HomepageLayoutBinding

class AddanimeFragment : Fragment(){

    private var _binding : AddAnimeLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddAnimeLayoutBinding.inflate(inflater, container,false)

        binding.AddAnimeBtn.setOnClickListener{
            findNavController().navigate(R.id.action_addanimeFragment_to_homepageFragment)
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