package co.danielbastidas.findstackusers.app

import android.app.Application
import android.content.Context
import co.danielbastidas.findstackusers.BuildConfig
import co.danielbastidas.findstackusers.activities.search.SearchActivity
import co.danielbastidas.findstackusers.activities.search.SearchComponent
import co.danielbastidas.findstackusers.activities.search.SearchModule
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailActivity
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailComponent
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailModule
import co.danielbastidas.findstackusers.app.api.APIModule
import co.danielbastidas.findstackusers.app.api.model.StackUser


class FindStackUsersApp: Application() {

    private lateinit var navigator:Navigator
    private lateinit var context:Context

    //SubComponents
    companion object {
        private lateinit var appComponent:AppComponent

        @JvmStatic
        fun createMainComponent(searchActivity: SearchActivity): SearchComponent =
                appComponent.plusSearchComponent(SearchModule(searchActivity))

         @JvmStatic
        fun createUserDetailComponent(activity: UserDetailActivity, user: StackUser): UserDetailComponent =
                appComponent.plusUserDetailComponent(UserDetailModule(activity, user))


    }

    override fun onCreate() {
        super.onCreate()

        navigator = Navigator()
        context = applicationContext

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(navigator))
                .aPIModule(APIModule(BuildConfig.SERVER_URL))
                .picassoModule(PicassoModule(context))
                .build()

    }


}

