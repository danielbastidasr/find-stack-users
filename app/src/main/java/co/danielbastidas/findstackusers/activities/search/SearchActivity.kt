package co.danielbastidas.findstackusers.activities.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.danielbastidas.findstackusers.activities.search.mvp.SearchPresenter
import co.danielbastidas.findstackusers.activities.search.mvp.view.SearchView
import co.danielbastidas.findstackusers.app.FindStackUsersApp
import co.danielbastidas.findstackusers.app.Navigator
import javax.inject.Inject

open class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var searchView: SearchView

    @Inject
    lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FindStackUsersApp.createMainComponent(this).inject(this)

        setContentView(searchView)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
