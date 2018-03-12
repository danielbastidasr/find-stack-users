package co.danielbastidas.findstackusers.app

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class PicassoModule(private val context: Context) {

    @AppScope
    @Provides
    fun providePicasso():Picasso {
        return Picasso.with(context)
    }
}