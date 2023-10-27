package com.example.w4_app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.w4_app.data.StoryRepository
import com.example.w4_app.data.model.UserModel
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: StoryRepository): ViewModel() {
    fun login(email: String, password: String) = repository.login(email, password)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

}