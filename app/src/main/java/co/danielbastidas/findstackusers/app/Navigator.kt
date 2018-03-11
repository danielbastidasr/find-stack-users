package co.danielbastidas.findstackusers.app

import android.app.Activity
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailActivity
import co.danielbastidas.findstackusers.app.api.model.StackUser


class Navigator {

    fun navigateToUserDetail(context: Activity, user:StackUser) {
        UserDetailActivity.start(context,user)
    }
}