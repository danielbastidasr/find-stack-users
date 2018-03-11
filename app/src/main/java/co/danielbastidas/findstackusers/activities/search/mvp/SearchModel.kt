package co.danielbastidas.findstackusers.activities.search.mvp

import co.danielbastidas.findstackusers.activities.search.SearchActivity
import co.danielbastidas.findstackusers.app.api.client.StackClient
import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList


class SearchModel(private val stackClient: StackClient, private val activity: SearchActivity) {

    private var currentName:String = ""
    private var users : List<StackUser> = ArrayList<StackUser>()

    fun getListUsersWithName(name:String):Observable<List<StackUser>>{

        if (currentName != name){
            return stackClient.getStackUsers(name)
                    .subscribeOn(Schedulers.io())
                    .map {
                        pagedResponse ->
                            users = pagedResponse.items
                            currentName = name

                        pagedResponse.items
                    }
        }
       return Observable.empty()
    }
}