package com.mpokket.repodetails

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mpokket.R
import com.mpokket.contributors.ContributorsDetailsActivity
import com.mpokket.databinding.RepoDetailsBinding
import com.mpokket.helper.Globals
import com.mpokket.helper.NetworkHelper
import com.mpokket.models.Item
import com.mpokket.web.WebViewActivity
import kotlinx.android.synthetic.main.toolbar.*

class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var dataBinding: RepoDetailsBinding
    private val item: Item
        get() = intent?.getSerializableExtra("item") as Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
        dataBinding.repoDetailsModel = item

        toolbar_actionbar.title = item.name
        setSupportActionBar(toolbar_actionbar)

        dataBinding.projLink.setOnClickListener {
            if (NetworkHelper.isNetworkConnected(this)) {
                startActivity(
                    Intent(this, WebViewActivity::class.java).putExtra(
                        "proj_link",
                        item.html_url
                    )
                )
            } else {
                Globals.showToastMessage(this, getString(R.string.no_internet))
            }
        }

        dataBinding.seeContributors.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ContributorsDetailsActivity::class.java
                ).putExtra("contributor_url", item.contributors_url)
            )
        }
    }
}
