package com.mpokket.koindi

import com.mpokket.viewmodel.ContributorInfoViewModel
import com.mpokket.viewmodel.ContributorsDetailsViewModel
import com.mpokket.viewmodel.SearchRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule =
    module(override = true) {

        viewModel {
            SearchRepositoriesViewModel(searchRepositoryService = get())
        }

        viewModel {
            ContributorsDetailsViewModel(searchRepositoryService = get())
        }

        viewModel {
            ContributorInfoViewModel(searchRepositoryService = get())
        }
    }
