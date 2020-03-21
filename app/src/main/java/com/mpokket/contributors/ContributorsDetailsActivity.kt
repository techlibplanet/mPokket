package com.mpokket.contributors

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpokket.R
import com.mpokket.helper.Globals
import com.mpokket.helper.NetworkHelper
import com.mpokket.models.ContributorsModel
import com.mpokket.network.ApiResult
import com.mpokket.viewmodel.ContributorsDetailsViewModel
import kotlinx.android.synthetic.main.activity_contributors_details.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContributorsDetailsActivity : AppCompatActivity(), ApiResult {

    private val viewModel by viewModel<ContributorsDetailsViewModel>()
    private val contributorsAdapter: ContributorsAdapter by lazy { ContributorsAdapter() }
    private val url: String?
        get() = intent.getStringExtra("contributor_url")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributors_details)

        toolbar_actionbar.title = "Contributors"
        setSupportActionBar(toolbar_actionbar)

        contributors_recycler_view.layoutManager = LinearLayoutManager(this)
        contributors_recycler_view.setHasFixedSize(true)
        contributors_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        contributors_recycler_view.adapter = contributorsAdapter

        when {
            NetworkHelper.isNetworkConnected(this) -> viewModel.getContributors(
                this,
                url!!
            )
            else -> Globals.showToastMessage(this, getString(R.string.no_internet))
        }
    }

    override fun onSuccess(data: Any) {
        setContributorsAdapter(data as List<ContributorsModel>)
    }

    private fun setContributorsAdapter(list: List<ContributorsModel>) {
        contributorsAdapter.items = list
        contributorsAdapter.notifyDataSetChanged()
        stopShimmer()
    }

    override fun onError(error: String?) {
        error?.let {
            stopShimmer()
            Globals.showToastMessage(this, it)
        }
    }

    private fun stopShimmer() {
        contributor_shimmer.stopShimmer()
        contributor_shimmer.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        contributor_shimmer.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        contributor_shimmer.stopShimmer()
    }
}
