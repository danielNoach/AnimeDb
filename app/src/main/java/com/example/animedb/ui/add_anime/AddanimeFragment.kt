package com.example.animedb.ui.add_anime

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.animedb.Animeitem
import com.example.animedb.R
import com.example.animedb.databinding.AddAnimeLayoutBinding
import com.example.animedb.ui.AnimeItemsViewModel

class AddanimeFragment : Fragment(){

    private var _binding : AddAnimeLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimeItemsViewModel by activityViewModels()

    private var imageUri : Uri? = null

    val pickImageLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()){
            binding.imageAdded.setImageURI(it)
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
        _binding = AddAnimeLayoutBinding.inflate(inflater, container,false)

        binding.AddAnimeBtn.setOnClickListener{
            if (isInputValid()){

                val anime = Animeitem(
                    binding.AnimeTitle.text.toString(),
                    binding.AnimeDesc.text.toString(),
                    binding.AnimeEpNumber.text.toString(),
                    binding.AnimeYearRel.text.toString(),
                    imageUri.toString()
                )

                viewModel.addAnimeItem(anime)

                findNavController().navigate(R.id.action_addanimeFragment_to_homepageFragment)

            }

        }

        binding.AddImageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }

        return binding.root
    }

    private fun isInputValid(): Boolean {

        if (binding.AnimeTitle.text.isNullOrEmpty()) {
            binding.AnimeTitle.error = getString(R.string.Title_validation)
            return false
        }
        if (binding.AnimeDesc.text.isNullOrEmpty()) {
            binding.AnimeDesc.error = getString(R.string.Desc_validation)
            return false
        }
        if (binding.AnimeEpNumber.text.isNullOrEmpty()) {
            binding.AnimeEpNumber.error = getString(R.string.Episode_validation)
            return false
        }
        if (binding.AnimeYearRel.text.isNullOrEmpty()) {
            binding.AnimeYearRel.error = getString(R.string.Year_validation)
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}