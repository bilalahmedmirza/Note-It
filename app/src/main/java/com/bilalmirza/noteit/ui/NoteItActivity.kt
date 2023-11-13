package com.bilalmirza.noteit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilalmirza.noteit.R
import com.bilalmirza.noteit.databinding.ActivityNoteitBinding

class NoteItActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteitBinding.inflate(layoutInflater)
        setTheme(R.style.Base_Theme_NoteIt)
        setContentView(binding.root)
    }
}