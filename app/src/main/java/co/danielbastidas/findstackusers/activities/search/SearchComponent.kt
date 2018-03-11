package co.danielbastidas.findstackusers.activities.search

import dagger.Subcomponent


@SearchScope
@Subcomponent(modules = [(SearchModule::class)])
interface SearchComponent {

    fun inject(searchActivity: SearchActivity)

}