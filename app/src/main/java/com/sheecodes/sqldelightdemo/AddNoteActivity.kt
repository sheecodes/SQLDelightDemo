package com.sheecodes.sqldelightdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sheecodes.sqldelightdemo.databinding.ActivityAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import demo.notesdb.NotesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDescription.text.toString()

            if (title.isNullOrEmpty() || description.isNullOrEmpty()){
               return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.insertNote(title, description)
                startActivity(Intent(this@AddNoteActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}