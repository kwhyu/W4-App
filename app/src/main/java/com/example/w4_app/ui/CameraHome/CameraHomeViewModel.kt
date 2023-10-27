package com.example.w4_app.ui.CameraHome

import androidx.lifecycle.ViewModel
import com.example.w4_app.data.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CameraHomeViewModel (private val storyRepository: StoryRepository): ViewModel(){
    fun postStory(file: MultipartBody.Part, description: RequestBody) = storyRepository.postStory(file, description)

}