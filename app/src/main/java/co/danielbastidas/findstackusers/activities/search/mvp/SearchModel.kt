package co.danielbastidas.findstackusers.activities.search.mvp

import android.util.Log
import co.danielbastidas.findstackusers.activities.search.SearchActivity
import co.danielbastidas.findstackusers.app.api.StackService
import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.Observable

import java.util.concurrent.TimeUnit


class SearchModel(private val stackService: StackService, private val activity: SearchActivity) {

    //TODO: Connect to stackService

    val listUsers = listOf<StackUser>(StackUser("01","Daniel","https://lh3.googleusercontent.com/-NfxRYAq6tCg/AAAAAAAAAAI/AAAAAAAAB7w/gmLVlsVA2VM/photo.jpg?sz=128"), StackUser("02","Jorge","https://www.gravatar.com/avatar/ef91c870c7f5a2b6fbf26f6b96e17e47?s=128&d=identicon&r=PG&f=1"),StackUser("03","Gregorio","https://www.gravatar.com/avatar/160194082ee602d5cc7fc5da6f8bdf94?s=128&d=identicon&r=PG&f=1"))
    var currentName:String = ""

    fun getListUsersWithName(name:String):Observable<List<StackUser>>{

        if (currentName != name){
            Log.d("CURRENTNAME","CURRENT NAME != NULL AND NOT SEARCHED PREVIOUSLY")
            currentName = name
            return Observable.just(listUsers).delay(4, TimeUnit.SECONDS)
        }
        Log.d("CURRENTNAME","CURRENT NAME WAS SEARCHED PREVIOUSLY")
        //return Observable.error(Throwable("HTTP"))
       return Observable.empty()


    }

}