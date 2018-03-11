package co.danielbastidas.findstackusers.app.api

import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface StackService {

    @GET("/users")
    fun getStackUsers(@Query("pagesize") pageSize: Int,
                      @Query("order") order: String,
                      @Query("sort") page: String,
                      @Query("inname") name: String,
                      @Query("site") site: String): Observable<List<StackUser>>
}