package co.danielbastidas.findstackusers.app.api

import co.danielbastidas.findstackusers.app.AppScope
import dagger.Module
import dagger.Provides


@Module
class APIModule {

    @AppScope
    @Provides
    fun provideStackService(): StackService {
        return StackClient().getStackService()
    }
}