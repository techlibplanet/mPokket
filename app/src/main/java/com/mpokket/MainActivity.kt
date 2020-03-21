package com.mpokket

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpokket.helper.NetworkHelper
import com.mpokket.models.SearchApiModel
import com.mpokket.searchrepository.SearchRepositoryAdapter
import com.mpokket.viewmodel.ApiResult
import com.mpokket.viewmodel.SearchRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ApiResult {

    private val searchRepositoryAdapter: SearchRepositoryAdapter by lazy { SearchRepositoryAdapter() }
    private val viewModel by viewModel<SearchRepositoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar_actionbar.title = "mPokket"
        setSupportActionBar(toolbar_actionbar)

        trending_repository_recycler_view.layoutManager = LinearLayoutManager(this)
        trending_repository_recycler_view.setHasFixedSize(true)
        trending_repository_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        trending_repository_recycler_view.adapter = searchRepositoryAdapter

        pullToRefresh.setOnRefreshListener {
            shimmer_view_container.visibility = View.VISIBLE;
            shimmer_view_container.startShimmer()
            when {
                NetworkHelper.isNetworkConnected(this) -> viewModel.getTrendingRepositories(
                    this,
                    DEFAULT_TOPIC
                )
                else -> showToastMessage("No Internet !")
            }
            pullToRefresh.isRefreshing = false
        }

        viewModel.getTrendingRepositories(this, DEFAULT_TOPIC)
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmer_view_container.stopShimmer()
    }

    private fun setTrendingRepositoryAdapter(searchApiModel: SearchApiModel) {
        searchRepositoryAdapter.items = searchApiModel.items
        searchRepositoryAdapter.notifyDataSetChanged()
        shimmer_view_container.stopShimmer()
        shimmer_view_container.visibility = View.GONE
    }

    override fun onSuccess(data: Any) {
        setTrendingRepositoryAdapter(data as SearchApiModel)
    }

    override fun onError(error: String?) {
        shimmer_view_container.stopShimmer()
        shimmer_view_container.visibility = View.GONE
        error?.let { showToastMessage(it) }
    }

    private fun showToastMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun filter(query: String) {
        if (NetworkHelper.isNetworkConnected(this)) {
            if (query.length >= 3) {
                shimmer_view_container.visibility = View.VISIBLE;
                shimmer_view_container.startShimmer()
                viewModel.getTrendingRepositories(this, query)
            }
        } else {
            showToastMessage("No Internet !")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DEFAULT_TOPIC = "kotlin"
    }
}
