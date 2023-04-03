package com.example.animedb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.animedb.databinding.AddAnimeLayoutBinding
import com.example.animedb.databinding.HomepageLayoutBinding

class AddanimeFragment : Fragment(){

    private var _binding : AddAnimeLayoutBinding? = null
    private val binding get() = _binding!!

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

            val animeitem = Animeitem(binding.AnimeTitle.text.toString(),
            binding.AnimeDesc.text.toString(),null,null,imageUri.toString())

            ItemManager.add(animeitem)

            findNavController().navigate(R.id.action_addanimeFragment_to_homepageFragment)
        }

        binding.AddImageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
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