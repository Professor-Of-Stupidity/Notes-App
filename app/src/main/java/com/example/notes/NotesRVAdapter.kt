package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener: iNotesRVAdapter) :RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>(){

    val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val delbtn = itemView.findViewById<ImageView>(R.id.deleteBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //we inflate the view
        //context is from the class constructor
        val myviewholder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))

        myviewholder.delbtn.setOnClickListener {
            listener.onItemClicked(allNotes[myviewholder.adapterPosition])
        }


        return myviewholder

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote = allNotes[position]
        holder.textView.text = currNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)

        //to notify and update the recycler view regardign the change
        notifyDataSetChanged()
    }

}

//we need an interface to handle clicks
interface iNotesRVAdapter
{
    fun onItemClicked(notes: Note)
}