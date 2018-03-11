package co.danielbastidas.findstackusers.activities.search.mvp

import android.os.Bundle
import co.danielbastidas.findstackusers.activities.search.SearchActivity
import co.danielbastidas.findstackusers.app.api.client.StackClient
import co.danielbastidas.findstackusers.app.api.model.StackUser
import co.danielbastidas.findstackusers.util.savestate.BundleAction
import co.danielbastidas.findstackusers.util.savestate.ReactiveSaveState
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList


open class SearchModel(private val stackClient: StackClient, private val activity: SearchActivity) {

    var currentName:String = ""
    private var users : List<StackUser> = ArrayList<StackUser>()
    private val reactiveSaveState = ReactiveSaveState()

    companion object {
        const val BUNDLE_LIST = "BUNDLE_LIST"
        const val BUNDLE_NAME = "BUNDLE_NAME"
    }

    /*GET USERS FROM API*/
    open fun getListUsersWithName(name:String):Observable<List<StackUser>>{

        if (currentName != name){

            return stackClient.getStackUsers(name)
                    .subscribeOn(Schedulers.io())
                    .map {
                        pagedResponse ->
                            users = pagedResponse.items
                            currentName = name

                            saveEventsState(users)

                        users
                    }

        }
       return Observable.empty()
    }

    /*SAVE MODEL STATE IN BUNDLE */
    open fun saveEventsState(list: List<StackUser>) {

        reactiveSaveState.updateSaveState(activity, object : BundleAction {
            override fun call(bundle: Bundle) {

                bundle.putParcelableArrayList(BUNDLE_LIST, list as ArrayList )
                bundle.putString(BUNDLE_NAME, currentName)

            }
        })

    }


    /*INI MODEL STATE */
    open fun getUsersFromSaveState(): Single<List<StackUser>> {
        return areUsersInSaveState()
                .isEmpty.flatMap {
            _ ->
            Single.just(users)
        }
    }

    /*GET MODEL STATE FROM THE BUNDLE */
    private fun areUsersInSaveState(): Maybe<List<StackUser>> {
        return reactiveSaveState.getSavedState(activity).map { bundle ->
            currentName = bundle.getString(BUNDLE_NAME)
            users = bundle.getParcelableArrayList(BUNDLE_LIST)
            users
        }
    }
}