package co.danielbastidas.findstackusers.app

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val navigator: Navigator) {

    @AppScope
    @Provides
    fun navigator(): Navigator {
        return navigator
    }
}