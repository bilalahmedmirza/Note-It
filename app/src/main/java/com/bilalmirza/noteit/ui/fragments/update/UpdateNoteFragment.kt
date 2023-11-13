package com.bilalmirza.noteit.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bilalmirza.noteit.R
import com.bilalmirza.noteit.databinding.FragmentUpdateNoteBinding
import com.bilalmirza.noteit.model.Note
import com.bilalmirza.noteit.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private var note: Note? = null
    private var title: String? = null
    private var id: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateNoteBinding.bind(view)

        //  GETTING ARGUMENTS FROM LIST FRAGMENT
        val args = UpdateNoteFragmentArgs.fromBundle(requireArguments())

        //  PUTTING NOTE AND TITLE IN THE VARIABLES
        note = args.note
        title = args.note.title
        id = args.note.id

        //  SETTING TITLE AND CONTENT IN THE UPDATE FRAGMENT
        binding.apply {
            title.setText(args.note.title)
            content.setText(args.note.content)
        }

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        //  DELETE NOTE BUTTON
        binding.deleteNoteButton.setOnClickListener {
            deleteNote()
        }

        //  UPDATE BUTTON
        binding.saveNoteButton.setOnClickListener {
            if (binding.title.text.isEmpty() && binding.content.text.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill at least one of the two fields.", Toast.LENGTH_SHORT).show()
            }
            else {
                updateNote()
            }
        }
    }

    private fun updateNote() {
        val updatedTitle = binding.title.text.toString()
        val updatedContent = binding.content.text.toString()

        val updatedNote = Note(id!!, updatedTitle, updatedContent)
        noteViewModel.updateNote(updatedNote)

        Toast.makeText(requireContext(), "Note Updated!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateNoteFragment_to_listFragment)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            noteViewModel.deleteNote(note!!)
            Toast.makeText(requireContext(), "Successfully deleted $title", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNoteFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete $title?")
        builder.setMessage("Are you sure you want to delete $title?")
        builder.create().show()
    }
}