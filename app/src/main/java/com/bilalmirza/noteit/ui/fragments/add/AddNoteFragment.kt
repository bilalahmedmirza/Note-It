package com.bilalmirza.noteit.ui.fragments.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bilalmirza.noteit.R
import com.bilalmirza.noteit.databinding.FragmentAddNoteBinding
import com.bilalmirza.noteit.model.Note
import com.bilalmirza.noteit.viewmodel.NoteViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNoteBinding.bind(view)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.saveNoteButton.setOnClickListener {
            if (binding.title.text.isEmpty() && binding.content.text.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill at least one of the two fields.", Toast.LENGTH_SHORT).show()
            }
            else {
                insertToDatabase()
            }
        }
    }

    private fun insertToDatabase() {
        val noteTitle = binding.title.text.toString()
        val noteContent = binding.content.text.toString()

        val note = Note(0, noteTitle, noteContent)
        noteViewModel.addNote(note)

        Toast.makeText(requireContext(), "Note Added!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_addNoteFragment_to_listFragment2)
    }
}