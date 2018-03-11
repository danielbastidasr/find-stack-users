package co.danielbastidas.findstackusers.app.api.client

import co.danielbastidas.findstackusers.app.api.StackService
import co.danielbastidas.findstackusers.app.api.model.PagedResponseBody
import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.Observable

class StackClient(baseURL:String) {
    private val stackService: StackService = StackRetrofitClient(baseURL).getStackService()

    fun getStackUsers(name:String):Observable<PagedResponseBody<List<StackUser>>> =
            stackService.getStackUsers(20,"asc","name",name,"stackoverflow")

}