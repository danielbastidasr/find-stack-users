package co.danielbastidas.findstackusers.activities.search

import co.danielbastidas.findstackusers.activities.search.mvp.SearchModel
import co.danielbastidas.findstackusers.activities.search.mvp.SearchPresenter
import co.danielbastidas.findstackusers.activities.search.mvp.view.SearchView
import co.danielbastidas.findstackusers.app.api.StackService
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
    fun model(stackService: StackService): SearchModel {
        return SearchModel(stackService, activity)
    }


    @Provides
    @SearchScope
    fun presenter(view: SearchView, model: SearchModel): SearchPresenter {
        return SearchPresenter(view, model)
    }

}