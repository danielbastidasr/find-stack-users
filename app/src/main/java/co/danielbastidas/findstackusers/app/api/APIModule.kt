package co.danielbastidas.findstackusers.app.api

import co.danielbastidas.findstackusers.app.AppScope
import co.danielbastidas.findstackusers.app.api.client.StackClient
import dagger.Module
import dagger.Provides


@Module
class APIModule(val baseURL:String) {

    @AppScope
    @Provides
    fun provideStackClient(): StackClient {
        return StackClient(baseURL)
    }
}