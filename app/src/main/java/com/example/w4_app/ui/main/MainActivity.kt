package com.example.w4_app.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w4_app.R
import com.example.w4_app.adapter.ListStoryAdapter
import com.example.w4_app.data.Result
import com.example.w4_app.data.model.ListStoryItem
import com.example.w4_app.databinding.ActivityMainBinding
import com.example.w4_app.ui.CameraHome.CameraHomeActivity
import com.example.w4_app.ui.login.LoginActivity
import com.example.w4_app.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>{
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = resources.getString(R.string.home)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                getStory()
                newStory()
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvStory.layoutManager = layoutManager

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                getStory()
            }
            R.id.menu2 -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            R.id.menu3 -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getStory(){
        viewModel.getStories().observe(this){story ->
            if(story != null){
                when(story){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        val listStory = story.data
                        storyAdapter(listStory)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        showToast(story.error)
                    }
                }
            }
        }
    }

    private fun storyAdapter(listStory: List<ListStoryItem>) {
        val adapter = ListStoryAdapter(this@MainActivity)
        adapter.submitList(listStory)
        binding.rvStory.adapter = adapter
    }

    private fun newStory() {
        binding.btnNewStory.setOnClickListener {
            startActivity(Intent(this, CameraHomeActivity::class.java))
        }
    }

    private fun logout() {
        viewModel.logout()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}