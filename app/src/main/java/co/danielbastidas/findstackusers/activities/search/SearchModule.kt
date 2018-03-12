package co.danielbastidas.findstackusers.activities.search

import co.danielbastidas.findstackusers.activities.search.mvp.SearchModel
import co.danielbastidas.findstackusers.activities.search.mvp.SearchPresenter
import co.danielbastidas.findstackusers.activities.search.mvp.view.SearchView
import co.danielbastidas.findstackusers.app.api.client.StackClient
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val activity: SearchActivity) {

    @Provides
    @SearchScope
    fun view(): SearchView {
        return SearchView(activity)
    }

    @Provides
    @SearchScope
    fun model(stackClient: StackClient): SearchModel {
        return SearchModel(stackClient, activity)
    }


    @Provides
    @SearchScope
    fun presenter(view: SearchView, model: SearchModel): SearchPresenter {
        return SearchPresenter(view, model)
    }

}