package co.danielbastidas.findstackusers.activities.userdetail.mvp

import android.graphics.Bitmap
import co.danielbastidas.findstackusers.app.api.model.StackUser
import com.squareup.picasso.Picasso
import io.reactivex.Single


class UserDetailModel(val user: StackUser,  val picasso: Picasso) {


    fun getUserImageBitmap(): Single<Bitmap> = Single.create {
        try {
            if (!it.isDisposed) {
                val bitmap: Bitmap = picasso.load(user.profileImage).get()
                it.onSuccess(bitmap)
            }
        } catch (e: Throwable) {
            if (!it.isDisposed)
                it.onError(e)
        }
    }


}