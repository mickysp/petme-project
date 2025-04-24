package com.example.petme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petme.databinding.ActivityAboutmeBinding

class AboutmeActivity : AppCompatActivity() {
    private lateinit var bindingAbout: ActivityAboutmeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAbout = ActivityAboutmeBinding.inflate(layoutInflater)
        setContentView(bindingAbout.root)

    }
}
