package com.example.animedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animedb.R
import com.example.animedb.databinding.HomepageLayoutBinding
import com.example.animedb.ui.adapter.AnimeAdapter
import com.google.android.material.snackbar.Snackbar

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

        binding.AddFloatingBtn.setOnClickListener{
            findNavController().navigate(R.id.action_homepageFragment_to_addanimeFragment)
        }


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.animeItems?.observe(viewLifecycleOwner){

            binding.homepageRecycler.adapter = AnimeAdapter(it, object : AnimeAdapter.AnimeListener{
                override fun onAnimeItemLongClick(index: Int) {
                    val animeItem =(binding.homepageRecycler.adapter as AnimeAdapter).animeItemAt(index)
                    viewModel.setAnimeItem(animeItem)
                    findNavController().navigate((R.id.action_homepageFragment_to_updateFragment))
                }

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
                val animeItem =(binding.homepageRecycler.adapter as AnimeAdapter).animeItemAt(viewHolder.adapterPosition)

                val builder = AlertDialog.Builder(viewHolder.itemView.context)
                builder.setTitle("Delete  ${animeItem.title}")
                builder.setMessage("Are you sure you want to delete ${animeItem.title} from your list?")
                builder.setPositiveButton("Yes") { dialog, which ->
                    viewModel.deleteAnimeItem(animeItem)
                }
                builder.setNegativeButton("No") { dialog, which ->
                    (binding.homepageRecycler.adapter as AnimeAdapter).notifyDataSetChanged()
                }
                builder.show()
            }

        }).attachToRecyclerView(binding.homepageRecycler)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}