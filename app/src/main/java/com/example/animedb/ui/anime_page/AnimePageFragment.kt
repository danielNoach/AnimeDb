package com.example.animedb.ui.anime_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.animedb.R
import com.example.animedb.databinding.AnimePageBinding
import com.example.animedb.ui.AnimeItemsViewModel

class AnimePageFragment: Fragment() {

    var _binding : AnimePageBinding? = null

    val viewModel: AnimeItemsViewModel by activityViewModels()
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

        viewModel.chosenAnimeItem.observe(viewLifecycleOwner){
            binding.title.text = it.title
            binding.description.text = it.description
            binding.epNumberAnimePage.text = it.number_of_episodes
            binding.relYearAnimePage.text = it.release_year
            Glide.with(requireContext()).load(it.photo).circleCrop().into(binding.photo)
        }

        binding.UpdateAnimeBtn?.setOnClickListener{
            findNavController().navigate(R.id.action_animePageFragment_to_updateFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}