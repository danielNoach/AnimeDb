package com.example.animedb.ui.update

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.animedb.Animeitem
import com.example.animedb.R
import com.example.animedb.databinding.FragmentUpdateBinding
import com.example.animedb.ui.AnimeItemsViewModel
import kotlin.math.log


class UpdateFragment : Fragment() {

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var imageUri : Uri? = null
    private var animeId: Int? = null

    private val viewModel: AnimeItemsViewModel by activityViewModels()

    private val pickImageLauncher : ActivityResultLauncher<Array<String>> =
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
        _binding = FragmentUpdateBinding.inflate(
            inflater, container, false
        )

        binding.UpdateImageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }

        binding.UpdateAnimeBtn.setOnClickListener{
            updateAnimeItem()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.chosenAnimeItem.observe(viewLifecycleOwner){
            binding.updateFragmentTitle?.setText("Update ${it.title}")
            binding.UpdateAnimeTitle.setText(it.title)
            binding.UpdateAnimeDesc.setText(it.description)
            binding.UpdateAnimeEpNumber.setText(it.number_of_episodes)
            binding.UpdateAnimeYearRel.setText(it.release_year)
            Glide.with(requireContext()).load(it.photo).circleCrop().into(binding.UpdateImageAdded)
            animeId = it.id
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateAnimeItem(){
        val anime = Animeitem(
            binding.UpdateAnimeTitle.text.toString(),
            binding.UpdateAnimeDesc.text.toString(),
            binding.UpdateAnimeEpNumber.text.toString(),
            binding.UpdateAnimeYearRel.text.toString(),
            imageUri.toString()
        )
        if(inputCheck(anime)){
            anime.id = animeId!!
            viewModel.updateAnimeItem(anime)
            Toast.makeText(requireContext(), "${anime.title} Updated Successfully", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_homepageFragment)
        }

    }
    private fun inputCheck(animeItem: Animeitem): Boolean{
        if (binding.UpdateAnimeTitle.text.isNullOrEmpty()) {
            binding.UpdateAnimeTitle.error = getString(R.string.Title_validation)
            return false
        }
        if (binding.UpdateAnimeDesc.text.isNullOrEmpty()) {
            binding.UpdateAnimeDesc.error = getString(R.string.Desc_validation)
            return false
        }
        if (binding.UpdateAnimeEpNumber.text.isNullOrEmpty()) {
            binding.UpdateAnimeEpNumber.error = getString(R.string.Episode_validation)
            return false
        }
        if (binding.UpdateAnimeYearRel.text.isNullOrEmpty()) {
            binding.UpdateAnimeYearRel.error = getString(R.string.Year_validation)
            return false
        }

        if (imageUri == null || imageUri.toString().isBlank()) {
            val layoutInflater = LayoutInflater.from(requireContext())
            val toastView = layoutInflater.inflate(R.layout.toat_error, null)
            val toast = Toast(requireContext())
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.view = toastView
            toast.show()
            return false
        }
        return true
    }

}