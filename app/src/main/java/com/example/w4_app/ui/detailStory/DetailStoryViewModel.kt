package com.example.w4_app.ui.detailStory

import androidx.lifecycle.ViewModel
import com.example.w4_app.data.StoryRepository

class DetailStoryViewModel (private val repository: StoryRepository) : ViewModel() {

    fun getDetailStory(id : String) = repository.getDetailStory(id)
}