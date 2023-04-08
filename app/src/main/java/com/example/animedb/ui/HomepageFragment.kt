package com.example.animedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animedb.R
import com.example.animedb.databinding.HomepageLayoutBinding
import com.example.animedb.ui.adapter.AnimeAdapter

class HomepageFragment : Fragment() {

    private var _binding : HomepageLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimeItemsViewModel by activityViewModels()

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


        viewModel.animeItems?.observe(viewLifecycleOwner){
            binding.homepageRecycler.adapter = AnimeAdapter(it, object : AnimeAdapter.AnimeListener{
                override fun onAnimeItemClick(index: Int) {
                    val animeItem =(binding.homepageRecycler.adapter as AnimeAdapter).animeItemAt(index)
                    viewModel.setAnimeItem(animeItem)
                    findNavController().navigate((R.id.action_homepageFragment_to_animePageFragment))
                }
            })
            binding.homepageRecycler.layoutManager = LinearLayoutManager(requireContext())

        }


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
//                ItemManager.remove(viewHolder.adapterPosition)
               // binding.homepageRecycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                val animeItem =(binding.homepageRecycler.adapter as AnimeAdapter).animeItemAt(viewHolder.adapterPosition)
                viewModel.deleteAnimeItem(animeItem)
            }

        }).attachToRecyclerView(binding.homepageRecycler)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}