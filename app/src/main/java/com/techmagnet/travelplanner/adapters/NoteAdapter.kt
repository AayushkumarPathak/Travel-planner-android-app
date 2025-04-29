package com.techmagnet.travelplanner.adapters

import androidx.recyclerview.widget.RecyclerView
import com.techmagnet.travelplanner.models.Note
import android.view.LayoutInflater
import android.view.ViewGroup
import com.techmagnet.travelplanner.databinding.ItemNoteBinding


class NoteAdapter(
    private val notes: List<Note>,
    private val onDeleteClicked: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }
    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.apply {
            textViewTripName.text = note.tripName
            textViewDescription.text = note.description
            textViewCreatedAt.text =
                java.text.DateFormat.getDateTimeInstance().format(note.createdAt)

            btnDeleteNote.setOnClickListener {
                onDeleteClicked(note)
            }
        }
    }
}