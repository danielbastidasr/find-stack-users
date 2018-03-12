package co.danielbastidas.findstackusers.app.api.client

import co.danielbastidas.findstackusers.app.api.StackService
import co.danielbastidas.findstackusers.app.api.model.PagedResponseBody
import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.Observable

open class StackClient(baseURL:String) {

    companion object {
        private const val PAGE_SIZE = 20
        private const val ORDER = "asc"
        private const val SORT = "name"
        private const val SITE = "stackoverflow"
    }

    private val stackService: StackService = StackRetrofitClient(baseURL).getStackService()

    fun getStackUsers(name:String):Observable<PagedResponseBody<List<StackUser>>> =
            stackService.getStackUsers(PAGE_SIZE, ORDER, SORT,name, SITE)

}