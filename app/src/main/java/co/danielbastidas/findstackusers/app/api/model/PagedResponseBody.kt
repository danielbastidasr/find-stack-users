package co.danielbastidas.findstackusers.app.api.model

/**
 * Created by dabasra on 11/03/2018.
 */

class PagedResponseBody<T>(val items: T, val hasMore: Boolean, val quotaMax: Int, val quotaRemaining: Int) {


}