package co.danielbastidas.findstackusers.activities.search.mvp

import co.danielbastidas.findstackusers.activities.search.mvp.view.SearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SearchPresenter(private val view: SearchView, private val model: SearchModel) {

    private val disposables = CompositeDisposable()

    fun onCreate(){
        subscribe(getIniState())
        subscribe(observeSearchButton())
        subscribe(observeUserDetail())
    }


    fun onDestroy(){
        disposables.clear()
    }

    private fun observeSearchButton():Disposable{
        return view.getObservableClickSearchUser()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    view.setSearchButtonNotClickable()
                }
                .flatMap{
                            val name = view.getUserNameTyped()

                    model.getListUsersWithName(name)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnEach {
                                if(it.isOnComplete) {
                                    view.setSearchButtonClickable()
                                }
                            }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    view.setSearchButtonClickable()
                }
                .retry()
                .subscribe{
                    list-> view.setUsersList(list)
                }
    }

    private fun observeUserDetail():Disposable{
        return view.getObservableClickDetailUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    user -> view.navigateToUserDetail(user)
                }
    }

    private fun getIniState():Disposable{
       return model.getUsersFromSaveState()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe{
                   list->
                   view.setUsersList(list)
                   view.setSearchName(model.getNameFromSavedState())
               }
    }

    private fun subscribe(subscription: Disposable){
        disposables.add(subscription)
    }
}