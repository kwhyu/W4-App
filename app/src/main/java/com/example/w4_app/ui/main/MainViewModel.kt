package com.example.w4_app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.w4_app.data.StoryRepository
import com.example.w4_app.data.model.UserModel
import kotlinx.coroutines.launch

class MainViewModel (private val repository: StoryRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getStories() = repository.getStory()

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}