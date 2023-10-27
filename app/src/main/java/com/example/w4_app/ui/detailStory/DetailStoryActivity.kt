package com.example.w4_app.ui.detailStory

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.w4_app.R
import com.example.w4_app.data.Result
import com.example.w4_app.data.model.DetailStoryResponse
import com.example.w4_app.databinding.ActivityDetailStoryBinding
import com.example.w4_app.utils.ViewModelFactory
import com.example.w4_app.utils.convertDate2

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding
    private val viewModel by viewModels<DetailStoryViewModel>(){
        ViewModelFactory.getInstance(applicationContext)
    }

    private var id: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.detailStory)

        binding.tvdesc.movementMethod = ScrollingMovementMethod();

        id = intent.getStringExtra(ID)

        viewModel.getDetailStory(id.toString()).observe(this){item ->
            if(item != null){
                when(item){
                    is Result.Loading ->{
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        val detailStory = item.data
                        setDetailStory(detailStory)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        showToast(item.error)
                    }
                }
            }
        }
    }

    private fun setDetailStory(detailstory: DetailStoryResponse){
        binding.previewimage.visibility = View.VISIBLE
        binding.apply {
            Glide.with(previewimage.context)
                .load(detailstory.story.photoUrl)
                .into(previewimage)
        }

        binding.tvname.visibility = View.VISIBLE
        binding.tvname.text = detailstory.story.name
        binding.tvdesc.visibility = View.VISIBLE
        binding.tvdesc.text = detailstory.story.description
        binding.tvdesc.visibility = View.VISIBLE
        binding.tvdate.visibility = View.VISIBLE
        binding.tvdate.text = detailstory.story.createdAt.convertDate2()

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val ID = ""
    }
}