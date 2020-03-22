package com.mpokket.contributors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpokket.R
import com.mpokket.databinding.ContributorInfoBinding
import com.mpokket.helper.Globals
import com.mpokket.helper.NetworkHelper
import com.mpokket.models.ContributorInfoModel
import com.mpokket.models.Item
import com.mpokket.network.ApiResult
import com.mpokket.repos.ReposAdapter
import com.mpokket.viewmodel.ContributorInfoViewModel
import com.mpokket.viewmodel.RepoApi
import com.mpokket.web.WebViewActivity
import kotlinx.android.synthetic.main.activity_contributor_info.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContributorInfoActivity : AppCompatActivity(), ApiResult, RepoApi {

    private lateinit var contributorInfoBinding: ContributorInfoBinding
    private val url: String?
        get() = intent.getStringExtra("contributor_info")
    private val repoUrl: String?
        get() = intent.getStringExtra("repo_url")
    private val viewModel by viewModel<ContributorInfoViewModel>()
    private val repoAdapter: ReposAdapter by lazy { ReposAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contributorInfoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_contributor_info)
        contributorInfoBinding.contributorInfoModel = ContributorInfoModel()
        toolbar_actionbar.title = "Contributor Details"
        setSupportActionBar(toolbar_actionbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        url?.let {
            if (NetworkHelper.isNetworkConnected(this)) {
                viewModel.getContributorInfo(this, it)
            } else {
                Globals.showToastMessage(this, getString(R.string.no_internet))
            }
        }
    }

    override fun onSuccess(data: Any) {
        contributorInfoBinding.contributorInfoModel = data as ContributorInfoModel
        contributorInfoBinding.executePendingBindings()
        toolbar_actionbar.title = data.name

        viewModel.getRepos(this, repoUrl!!)

        contributorInfoBinding.knowMore.setOnClickListener {
            if (NetworkHelper.isNetworkConnected(this)) {
                startActivity(
                    Intent(this, WebViewActivity::class.java).putExtra(
                        "proj_link",
                        data.html_url
                    )
                )
            } else {
                Globals.showToastMessage(this, getString(R.string.no_internet))
            }
        }

    }

    override fun onError(error: String?) {
        error?.let { Globals.showToastMessage(this, it) }
    }

    override fun onApiCallSucces(data: Any) {
        repos_recycler_view.layoutManager = LinearLayoutManager(this)
        repos_recycler_view.setHasFixedSize(true)
        repos_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        repos_recycler_view.adapter = repoAdapter

        setTrendingRepositoryAdapter(data as List<Item>)
    }

    private fun setTrendingRepositoryAdapter(repoModel: List<Item>) {
        repoAdapter.items = repoModel
        repoAdapter.notifyDataSetChanged()
    }

    override fun onApiCallError(error: String?) {
        error?.let { Globals.showToastMessage(this, it) }
    }
}
