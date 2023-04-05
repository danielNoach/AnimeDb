package com.example.animedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animedb.databinding.HomepageLayoutBinding

class HomepageFragment : Fragment() {

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

        binding.homepageRecycler.adapter = AnimeAdapter(ItemManager.Animes, object : AnimeAdapter.AnimeListener{
            override fun onAnimeItemClick(index: Int) {
                findNavController().navigate((R.id.action_homepageFragment_to_animePageFragment)
                    , bundleOf("anime" to index))            }

        })
        binding.homepageRecycler.layoutManager = LinearLayoutManager(requireContext())


        ItemTouchHelper(object : ItemTouchHelper.Callback(){

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                ItemManager.remove(viewHolder.adapterPosition)
                binding.homepageRecycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(binding.homepageRecycler)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}