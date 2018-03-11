package co.danielbastidas.findstackusers.activities.search.mvp.view

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import co.danielbastidas.findstackusers.R
import co.danielbastidas.findstackusers.activities.search.SearchActivity
import co.danielbastidas.findstackusers.app.api.model.StackUser
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

@SuppressLint("ViewConstructor")
open class SearchView(private val activity: SearchActivity): FrameLayout(activity) {

    private val userAdapter:UserAdapter

    private val recyclerView:RecyclerView
    private val buttonSearch:Button
    private val userInput:EditText

    private val observeClickDetailUser:PublishSubject<StackUser>
    private val observeButtonSearch:Observable<Any>


    init {

        val view = inflate(activity, R.layout.activity_search,this)

        buttonSearch = view.findViewById(R.id.button_search)
        userInput = view.findViewById(R.id.edit_search)
        recyclerView = view.findViewById(R.id.list_users)

        //Observables
        observeButtonSearch = RxView.clicks(buttonSearch)
        observeClickDetailUser = PublishSubject.create<StackUser>()

        //RecyclerView
        userAdapter = UserAdapter(observeClickDetailUser)
        setUpRecyclerView()
    }

    open fun getUserNameTyped():String =
            userInput.text.toString()

    open fun navigateToUserDetail(user:StackUser) =
            activity.navigator.navigateToUserDetail(activity,user)


    open fun setUsersList(users:List<StackUser>) =
            userAdapter.setUsers(users)

    open fun setSearchButtonNotClickable(){

        buttonSearch.isClickable = false
        buttonSearch.setBackgroundColor(ContextCompat.getColor(context, R.color.material_grey_300))
        buttonSearch.text = resources.getText(R.string.button_search_not_clickable)
    }

    open fun setSearchButtonClickable(){

        buttonSearch.isClickable = true
        buttonSearch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        buttonSearch.text = resources.getText(R.string.button_search_clickable)
    }

    open fun setSearchName(name:String){
        userInput.setText(name)
    }

    open fun getObservableClickDetailUser():PublishSubject<StackUser>{
        return observeClickDetailUser
    }

    open fun getObservableClickSearchUser():Observable<Any>{
        return observeButtonSearch
    }

    private fun setUpRecyclerView(){

        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayout.VERTICAL

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = userAdapter
    }
}