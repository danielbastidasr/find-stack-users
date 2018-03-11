package co.danielbastidas.findstackusers.activities.userdetail

import co.danielbastidas.findstackusers.activities.userdetail.mvp.UserDetailModel
import co.danielbastidas.findstackusers.activities.userdetail.mvp.UserDetailPresenter
import co.danielbastidas.findstackusers.activities.userdetail.mvp.UserDetailView
import co.danielbastidas.findstackusers.app.api.model.StackUser
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class UserDetailModule (private val activity: UserDetailActivity, private val user: StackUser) {

    @Provides
    @UserDetailScope
    fun view(): UserDetailView {
        return UserDetailView(activity)
    }

    @Provides
    @UserDetailScope
    fun model(picasso: Picasso): UserDetailModel {
        return UserDetailModel(user, picasso)
    }


    @Provides
    @UserDetailScope
    fun presenter(view: UserDetailView, model: UserDetailModel): UserDetailPresenter {
        return UserDetailPresenter(view, model)
    }

}