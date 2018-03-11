package co.danielbastidas.findstackusers.app

import co.danielbastidas.findstackusers.activities.search.SearchComponent
import co.danielbastidas.findstackusers.activities.search.SearchModule
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailComponent
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailModule
import co.danielbastidas.findstackusers.app.api.APIModule
import co.danielbastidas.findstackusers.app.api.PicassoModule
import co.danielbastidas.findstackusers.app.api.client.StackClient
import com.squareup.picasso.Picasso
import dagger.Component


@AppScope
@Component(modules = [(AppModule::class), (APIModule::class), (PicassoModule::class)])
interface AppComponent {

    fun stackClient(): StackClient

    fun navigator(): Navigator

    fun picasso():Picasso

    //SubComponents
    fun plusSearchComponent(searchModule: SearchModule): SearchComponent
    fun plusUserDetailComponent(userDetailModule: UserDetailModule): UserDetailComponent

}