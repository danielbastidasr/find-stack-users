package co.danielbastidas.findstackusers.app.api

import android.content.Context
import co.danielbastidas.findstackusers.app.AppScope
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