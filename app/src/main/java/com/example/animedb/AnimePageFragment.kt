package com.example.animedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.animedb.databinding.AnimePageBinding

class AnimePageFragment: Fragment() {

    var _binding : AnimePageBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AnimePageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("anime")?.let{
            val item = ItemManager.Animes[it]

            binding.title.text = item.title
            binding.description.text = item.description
            Glide.with(requireContext()).load(item.photo).circleCrop().into(binding.photo)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}