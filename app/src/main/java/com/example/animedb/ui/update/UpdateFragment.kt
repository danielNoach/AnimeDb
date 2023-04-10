package com.example.animedb.ui.update

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.animedb.databinding.FragmentUpdateBinding
import com.example.animedb.ui.AnimeItemsViewModel


class UpdateFragment : Fragment() {

    var _binding : FragmentUpdateBinding? = null

    val viewModel: AnimeItemsViewModel by activityViewModels()
    val binding get() = _binding!!

    private var imageUri : Uri? = null


    val pickImageLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()){
            binding.UpdateImageAdded.setImageURI(it)
            if (it != null) {
                requireActivity().contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            imageUri = it
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.UpdateImageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.chosenAnimeItem.observe(viewLifecycleOwner){
            binding.UpdateAnimeTitle.setText(it.title)
            binding.UpdateAnimeDesc.setText(it.description)
            binding.UpdateAnimeEpNumber.setText(it.number_of_episodes)
            binding.UpdateAnimeYearRel.setText(it.release_year)
            Glide.with(requireContext()).load(it.photo).circleCrop().into(binding.UpdateImageAdded)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}