package com.bilalmirza.noteit.ui.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilalmirza.noteit.adapter.ListAdapter
import com.bilalmirza.noteit.R
import com.bilalmirza.noteit.databinding.FragmentListBinding
import com.bilalmirza.noteit.viewmodel.NoteViewModel

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var binding: FragmentListBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        //  RECYCLER VIEW
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //  NOTE VIEW MODEL
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        noteViewModel.readAllNote.observe(viewLifecycleOwner) { note ->
            adapter.updateData(note)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addNoteFragment)
        }
    }
}