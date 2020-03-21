package com.mpokket.repodetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mpokket.R
import com.mpokket.databinding.RepoDetailsBinding
import com.mpokket.models.Item
import kotlinx.android.synthetic.main.toolbar.*

class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var dataBinding: RepoDetailsBinding
    private val item: Item
        get() = intent?.getSerializableExtra("item") as Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        dataBinding.repoDetailsModel = item

        toolbar_actionbar.title = "Repo Details"
        setSupportActionBar(toolbar_actionbar)

    }
}
