package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), iNotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noterview = findViewById<RecyclerView>(R.id.notesRV)
        noterview.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        noterview.adapter = adapter



        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->list?.let{

            adapter.updateList(it)
         }
        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = findViewById<EditText>(R.id.input).text.toString()
        if (noteText.isNotEmpty())
        {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "Note Inserted", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this, "Please Enter Some Text!", Toast.LENGTH_SHORT).show()
        }
    }
}