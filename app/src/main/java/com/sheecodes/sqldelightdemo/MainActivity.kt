package com.sheecodes.sqldelightdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sheecodes.sqldelightdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NotesAdapter(NotesAdapter.OnClickListener { note ->
            viewModel.deleteNote(note.id)
        })

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.notes.collect { notes ->
                adapter.submitList(notes)
                binding.notesRecycler.adapter = adapter
            }
        }

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }
}