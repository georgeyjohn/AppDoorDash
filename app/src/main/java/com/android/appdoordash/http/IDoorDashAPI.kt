package com.android.appdoordash.http

import com.android.appdoordash.entities.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDoorDashAPI {
    val BASEURL: String
        get() = "https://api.doordash.com/"


    @GET("v2/restaurant/")
    fun getRestaurants(@Query("lat") lat: Double, @Query("lng") lng: Double): Call<List<Restaurant>>


    @GET("v2/restaurant/{id}/")
    fun getRestaurantDetails(@Path("id") restaurantId: Int): Call<Restaurant>
}