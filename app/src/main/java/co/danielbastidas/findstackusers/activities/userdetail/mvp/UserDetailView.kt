package co.danielbastidas.findstackusers.activities.userdetail.mvp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.FrameLayout
import co.danielbastidas.findstackusers.R
import co.danielbastidas.findstackusers.activities.userdetail.UserDetailActivity
import co.danielbastidas.findstackusers.app.api.model.StackUser
import kotlinx.android.synthetic.main.activity_user_detail.view.*

@SuppressLint("ViewConstructor")
class UserDetailView(private val activity: UserDetailActivity):FrameLayout(activity) {

    init {
        inflate(activity,R.layout.activity_user_detail,this)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setUser(stackUser: StackUser){

        activity.supportActionBar?.title = stackUser.name

        tvUserName.text = stackUser.name
        tvUserReputation.text = stackUser.name
        tvUserBadges.text = stackUser.name
        tvUserLocation.text = stackUser.name
        tvUserAge.text = stackUser.name
        tvUserCreation.text = stackUser.name

    }

    fun setImage(userImage:Bitmap){
        ivUserImage.setImageBitmap(userImage)
    }

}