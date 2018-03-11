package co.danielbastidas.findstackusers.activities.search.mvp

import co.danielbastidas.findstackusers.activities.search.mvp.view.SearchView
import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import co.danielbastidas.findstackusers.util.RxImmediateSchedulerRule
import io.reactivex.subjects.PublishSubject


class SearchPresenterTest{

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var model:SearchModel

    @Mock
    lateinit var view: SearchView

    lateinit var presenter:SearchPresenter

    private val mockUsers:List<StackUser> = listOf()


    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = SearchPresenter(view,model)
    }


    @Test
    fun onGetUsers(){
        val name = "name"

        //Given Text Input
        Mockito.`when`(model.getUsersFromSaveState()).thenReturn(Single.never())
        Mockito.`when`(model.getListUsersWithName(name)).thenReturn(Observable.just(mockUsers))

        Mockito.`when`(view.getObservableClickDetailUser()).thenReturn(PublishSubject.create())
        Mockito.`when`(view.getUserNameTyped()).thenReturn(name)

        //When User Click
        Mockito.`when`(view.getObservableClickSearchUser()).thenReturn(Observable.just(true))
        presenter.onCreate()

        //Then the following methods should be called
        Mockito.verify<SearchView>(view, Mockito.times(1)).setSearchButtonNotClickable()
        Mockito.verify<SearchView>(view, Mockito.times(1)).setSearchButtonClickable()
        Mockito.verify<SearchView>(view, Mockito.times(1)).setUsersList(mockUsers)

        //At the end we just destroy
        presenter.onDestroy()
    }


}