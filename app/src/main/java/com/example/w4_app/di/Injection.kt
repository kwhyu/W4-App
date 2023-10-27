package com.example.w4_app.di

import android.content.Context
import com.example.w4_app.data.StoryRepository
import com.example.w4_app.data.api.ApiConfig
import com.example.w4_app.utils.UserPreferences
import com.example.w4_app.utils.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val pref = UserPreferences.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoryRepository.getInstance(pref,apiService)
    }
}