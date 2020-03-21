package com.mpokket.koindi

import com.mpokket.viewmodel.SearchRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule =
    module(override = true) {

        viewModel {
            SearchRepositoriesViewModel(searchRepositoryService = get())
        }
    }
